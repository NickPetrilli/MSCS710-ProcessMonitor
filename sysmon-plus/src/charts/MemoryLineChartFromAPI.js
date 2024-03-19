import React, { useState, useEffect } from 'react';
import { Line } from 'react-chartjs-2';
import { Chart, CategoryScale, LinearScale, PointElement, LineElement, Tooltip, Legend } from 'chart.js';

// NEEDED to render the chart.  Sets the scale and other metadata for chart.
Chart.register(CategoryScale, LinearScale, PointElement, LineElement, Tooltip, Legend);


const MemoryLineChartFromAPI = () => {
  const [chartData, setChartData] = useState(null);

  const fetchData = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/v1/memory/chart');
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      const data = await response.json();
      if (data.length === 0) {
        setChartData(null); // No data available
        console.log("no data available")
        return;
      }
      //const labels = memoryUtilizationList.map((_, index) => `Time ${index + 1}`);

      let memoryUtilizationList = [];
      let labels = [];

      data.forEach((element) => {
          memoryUtilizationList.push(element.utilization);
          labels.push(element.timestamp);
      })
      setChartData({
        labels, // Generated labels for each data point
        datasets: [
          {
            label: 'Memory Usage',
            data: memoryUtilizationList, // The dataset is the array of CPU utilization values
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
      <h3>Memory Usage Over Time</h3>
      <Line data={chartData} />
    </div>
  );
};

export default MemoryLineChartFromAPI;