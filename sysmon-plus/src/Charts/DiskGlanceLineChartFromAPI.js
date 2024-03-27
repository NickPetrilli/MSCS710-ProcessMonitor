import React, { useState, useEffect } from 'react';
import { Line } from 'react-chartjs-2';
import { Chart, CategoryScale, LinearScale, PointElement, LineElement, Tooltip, Legend } from 'chart.js';
import { diskNameForChart } from '../Pages/Glance/DiskGlance.js';

// NEEDED to render the chart.  Sets the scale and other metadata for chart.
Chart.register(CategoryScale, LinearScale, PointElement, LineElement, Tooltip, Legend);


const DiskGlanceLineChartFromAPI = () => {
  const [chartData, setChartData] = useState(null);
  const [disk, setDisk] = useState([]);

        const fetchData = async () => {
          // Fetch disk utilization from API endpoint
          fetch('http://localhost:8080/api/v1/disk')
            .then(response => response.json())
            .then(data => {
              // Update the JSON object with fetched data
              setDisk(data);

              if (data && data.length > 0) {
                fetchChartData(); // Ensure we have data and then call fetchChartData
              }
            })
            .catch(error => {
              console.error('Error in GET request for general disk information:', error);
            });
        };

  const fetchChartData = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/v1/disk/chart/' + disk[0].name);
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      const data = await response.json();
      if (data.length === 0) {
        setChartData(null); // No data available
        console.log("no data available")
        return;
      }

      let diskUtilizationList = [];
      let labels = [];


      data.forEach((element) => {
            diskUtilizationList.push(element.utilization);
            labels.push(element.timestamp);
      })

      setChartData({
        labels, // Generated labels for each data point
        datasets: [
          {
            label: 'Disk Usage',
            data: diskUtilizationList, // The dataset is the array of disk utilization values
            fill: true,
            borderColor: 'rgb(75, 192, 192)',
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
        fetchData();
        const interval = setInterval(fetchData, 10000); // Refresh data every 10000ms (10 seconds)

        return () => clearInterval(interval); // Cleanup interval on component unmount
      }, []);

  if (chartData === null) {
    return <div>No data available to display.</div>;
  }

  return (
    <div className='glance-graph'>
      <h3>Disk Usage Over Time</h3>
      <Line data={chartData} />
    </div>
  );
  }

export default DiskGlanceLineChartFromAPI;