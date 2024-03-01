import React, { useState, useEffect } from 'react';
import graphTemplate from '../../Images/Capping GRAPH TEMPLATE.png'

const DiskSection = () => {
    const [disk, setDisk] = useState([]);
    const [topProcesses, setTopProcesses] = useState([]);

    useEffect(() => {
      // Fetch data from an API endpoint
      fetch('http://localhost:8080/api/v1/disk')
        .then(response => response.json())
        .then(data => {
          // Update the JSON object with fetched data
          setDisk(data);
        })
        .catch(error => {
          console.error('Error fetching memory data:', error);
        });
  
      // Fetch data from an API endpoint
      fetch('http://localhost:8080/api/v1/disk/top-processes')
        .then(response => response.json())
        .then(data => {
          // Update the JSON object with fetched data
          setTopProcesses(data);
        })
        .catch(error => {
          console.error('Error fetching memory top process data:', error);
        });
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
      <h1 className="title">Disk</h1>
      {disk.length > 0 ? (<h4>{disk[0].model}</h4>) : (<p>No data available</p>)}

        <div>
          {/* Graph side */}
          <div className="row">
            <img src={graphTemplate} alt="" className="graph" />

            {/* Utilization / Top Processes Side */}
            <div className=".utilandTopProc-sec">
              <div className="row">
                <div className="utilBox" style={{ backgroundColor }}> {util}% Utilization </div>
              </div>

              <h4 className="top-processes-table-TITLE"> Top Processes </h4>
              <table className="top-processes-table">
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
