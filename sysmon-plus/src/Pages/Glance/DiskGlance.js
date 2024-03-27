import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import DiskGlanceLineChartFromAPI from '../../Charts/DiskGlanceLineChartFromAPI';

const DiskSection = () => {

  const [disk, setDisk] = useState([]);
  const [diskNameForChart, setDiskNameForChart] = useState(null);
  const [topProcesses, setTopProcesses] = useState([]);

    useEffect(() => {
      const fetchData = () => {
        // Fetch disk utilization from API endpoint
        fetch('http://localhost:8080/api/v1/disk')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setDisk(data);
            setDiskNameForChart(data[0].name);
          })
          .catch(error => {
            console.error('Error in GET request for general disk information:', error);
          });

        // Fetch top 3 processes (based on disk usage) from an API endpoint
        fetch('http://localhost:8080/api/v1/disk/top-processes')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setTopProcesses(data);
          })
          .catch(error => {
            console.error('Error in GET request for disk top processes:', error);
          });
      }

    // Run the function immediately when the component mounts
    fetchData();

    // Set up interval to run the function every 10 seconds
    const intervalId = setInterval(fetchData, 10000);

    return () => clearInterval(intervalId);
    }, []); // Empty dependency array means this effect runs once after the first render

        // Logging
        console.error({disk});

        // Directly initializing text
        var util = Math.floor(disk.utilization);
      
        const getUtilBackgroundColor = (percentage) => {
          if (percentage < 40) {
            return 'green';
          } else if (percentage >= 40 && percentage < 80) {
            return 'orange';
          } else {
            return 'red';
          }
        };
      
        var backgroundColor = getUtilBackgroundColor(util);

    return (
      <div className="section">
      <Link to="/disk-detail"> <h1> Disk </h1> </Link> 
      <Link to="/disk-detail"> {disk.length > 0 ? (<h4>{disk[0].model}</h4>) : (<p>No data available</p>)} </Link>

        <div>
          {/* Graph side */}
          <div className="row">
            <DiskGlanceLineChartFromAPI />

            {/* Utilization / Top Processes Side */}
            <div className=".utilandTopProc-sec">
              <div className="row">
              <Link to="/disk-detail"> {disk.length > 0 ? (<div className="utilBox" style={{ backgroundColor }}> {disk[0].utilization.toFixed(5)}% Utilization </div>) : (<p>No data available</p>)} </Link>
              </div>

              <table className="top-processes-table">
                <Link to="/cpu-processes"> <caption className="top-processes-table-TITLE"> Top Processes </caption> </Link>
                <tbody>
                {topProcesses.length > 0 ? (
                          topProcesses.map((item, index) => (
                            <tr key={item.id} className = "top-processes-table-row">
                              <td>{item.name}</td>
                              <td>{(item.diskSpeed / 1000000).toFixed(1)} MB/s</td>
                            </tr>
                          ))
                      ) : (
                      <p>No data available</p>
                  )}
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    );
};

export default DiskSection;
