import React, { useState, useEffect } from 'react';
import { Line } from 'react-chartjs-2';
import { Chart, CategoryScale, LinearScale, PointElement, LineElement, Tooltip, Legend } from 'chart.js';

// NEEDED to render the chart.  Sets the scale and other metadata for chart.
Chart.register(CategoryScale, LinearScale, PointElement, LineElement, Tooltip, Legend);


const CpuLineChartFromAPI = () => {
  const [chartData, setChartData] = useState(null);

  const fetchData = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/v1/cpu/chart');
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      const data = await response.json();
      if (data.length === 0) {
        setChartData(null); // No data available
        console.log("no data available")
        return;
      }

      let cpuUtilizationList = [];
      let labels = [];

      data.forEach((element) => {
            cpuUtilizationList.push(element.utilization);
            labels.push(element.timestamp);
      })

      //const labels = cpuUtilizationList.map((timestamp) => `Time ${timestamp}`);
      setChartData({
        labels, // Generated labels for each data point
        datasets: [
          {
            label: 'CPU Usage',
            data: cpuUtilizationList, // The dataset is the array of CPU utilization values
            fill: true,
            borderColor: 'rgb(75, 192, 192)',
            tension: 0.1
          }
        ]
      });
    } catch (error) {
      console.error("Error fetching data:", error);
      setChartData(null); // Handle error by resetting the chart data
    }
  };

  useEffect(() => {
        fetchData(); // Fetch data on component mount
        const interval = setInterval(fetchData, 10000); // Refresh data every 10000ms (10 seconds)

        return () => clearInterval(interval); // Cleanup interval on component unmount
      }, []);

  if (chartData === null) {
    return <div>No data available to display.</div>;
  }

  return (
    <div style={{ width: '500px' }}>
      <h3>CPU Usage Over Time</h3>
      <Line data={chartData} />
    </div>
  );
};

export default CpuLineChartFromAPI;