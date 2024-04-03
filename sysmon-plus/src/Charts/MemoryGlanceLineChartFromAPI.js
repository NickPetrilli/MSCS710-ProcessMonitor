import React, { useState, useEffect } from 'react';
import { Line } from 'react-chartjs-2';
import { Chart, CategoryScale, PointElement, LineElement, Tooltip, Legend, Filler } from 'chart.js';

// NEEDED to render the chart.  Sets the scale and other metadata for chart.
Chart.register(CategoryScale, PointElement, LineElement, Tooltip, Legend, Filler);


const MemoryGlanceLineChartFromAPI = () => {
  const [chartData, setChartData] = useState(null);

  const chartOptions = {
    responsive: true,
    maintainAspectRatio: true,
    scales: {
      y: {
        min: 0, // Set custom minimum value for y-axis
        max: 100, // Set custom maximum value for y-axis
        ticks: {
          stepSize: 10,
        }
      }
    }
  };

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
            borderColor: 'rgb(153, 255, 51)',
            backgroundColor: 'rgb(102, 255, 102, 0.75)',
            tension: 0.1
          }
        ],

        options : {
          responsive: true,
          keepAspectRatio: true,

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
        fetchData(); // Fetch data on component mount
        const interval = setInterval(fetchData, 10000); // Refresh data every 10000ms (10 seconds)

        return () => clearInterval(interval); // Cleanup interval on component unmount
      }, []);

  if (chartData === null) {
    return <div>No data available to display.</div>;
  }

  return (
    <div className='glance-graph'>
      <h3>Memory Usage Over Time</h3>
      <Line data={chartData} options={chartOptions}/>
    </div>
  );
};

export default MemoryGlanceLineChartFromAPI;