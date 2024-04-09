import React, { useState, useEffect } from 'react';
import { Line } from 'react-chartjs-2';
import { Chart, CategoryScale, LinearScale, PointElement, LineElement, Tooltip, Legend, Filler } from 'chart.js';

// NEEDED to render the chart.  Sets the scale and other metadata for chart.
Chart.register(CategoryScale, LinearScale, PointElement, LineElement, Tooltip, Legend, Filler);


const DiskLineChartFromAPI = ({ diskName, view }) => {
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
        suggestedMax: 100000,
        ticks: {
          stepSize: 10,
        }
      }
    }
  };

  const fetchChartData = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/v1/disk/chart/' + diskName);
      if (!response.ok) {
        throw new Error('Network response was not ok. Disk name is required to fetch chart data.');
      }
      const data = await response.json();
      if (data.length === 0) {
        setChartData(null); // No data available
        console.log("no data available")
        return;
      }

      let diskReadSpeedList = [];
      let diskWriteSpeedList = [];
      let labels = [];


      data.forEach((element) => {
            diskReadSpeedList.push((element.readSpeed / 1000).toFixed(1));
            diskWriteSpeedList.push((element.writeSpeed / 1000).toFixed(1));
            labels.push(element.timestamp);
      })

      setChartData({
        labels, // Generated labels for each data point
        datasets: [
          {
            label: 'Disk Read Speed (KB/s)',
            data: diskReadSpeedList, 
            fill: true,
            borderColor: 'rgb(75, 192, 192)',
            tension: 0.1
          },
          {
            label: 'Disk Write Speed (KB/s)',
            data: diskWriteSpeedList, // The dataset is the array of disk utilization values
            fill: true,
            borderColor: 'rgb(235, 52, 82)',
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
        fetchChartData();
        const interval = setInterval(fetchChartData, 10000); // Refresh data every 10000ms (10 seconds)

        return () => clearInterval(interval); // Cleanup interval on component unmount
      });

  if (chartData === null) {
    return <div>No data available to display.</div>;
  }

  return (
    <div className={view === 'detail' ? 'graph-detail' : 'glance-graph-disk'}>
      <h3>Disk Usage Over Time</h3>
      <Line data={chartData} options={chartOptions}/>
    </div>
  );
  }

export default DiskLineChartFromAPI;