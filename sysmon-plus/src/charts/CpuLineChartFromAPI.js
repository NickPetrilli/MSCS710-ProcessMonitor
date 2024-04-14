import React, { useState, useEffect } from 'react';
import { Line } from 'react-chartjs-2';
import { Chart, CategoryScale, PointElement, LineElement, Tooltip, Legend, Filler } from 'chart.js';

// NEEDED to render the chart.  Sets the scale and other metadata for chart.
Chart.register(CategoryScale, PointElement, LineElement, Tooltip, Legend, Filler);


const CpuLineChartFromAPI = ({view}) => {
  const [chartData, setChartData] = useState(null);

  const chartOptions = {
    responsive: true,
    maintainAspectRatio: true,
    scales: {
      x: {
        type: 'category',
        reverse: true
      },

      y: {
        min: 0, // Set custom minimum value for y-axis
        max: 100, // Set custom maximum value for y-axis
        ticks: {
          stepSize: 10
        }
      }
    }
  };

  const fetchChartData = async () => {
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
            borderColor: 'rgb(0, 153, 255)',
            backgroundColor: 'rgb(51, 204, 255, 0.75)',
            tension: 0.1
          }
        ],

        options : {
          responsive: true,
          // keepAspectRatio: true,

          scales: {
            y: {
              min: 0,
              max: 100,
              ticks: {
                stepSize: 10.0
              }
            }
          }
        }
      });   
    } catch (error) {
      console.error("Error fetching data:", error);
      setChartData(null); // Handle error by resetting the chart data
    }
  };

  useEffect(() => {
        fetchChartData(); // Fetch data on component mount
        const interval = setInterval(fetchChartData, 10000); // Refresh data every 10000ms (10 seconds)

        return () => clearInterval(interval); // Cleanup interval on component unmount
      }, []);

  if (chartData === null) {
    return <div>No data available to display.</div>;
  }

  return (
    <div className={view === 'detail' ? 'graph-detail' : 'glance-graph'}>
      <h3>CPU Usage Over Time</h3>
      <Line data={chartData} options={chartOptions}/>
    </div>
  );
};

export default CpuLineChartFromAPI;