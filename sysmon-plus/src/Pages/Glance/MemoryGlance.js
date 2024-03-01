// memSection.js
import React, { useState, useEffect } from 'react';
import graphTemplate from '../../Images/Capping GRAPH TEMPLATE.png'

const MemSection = () => {

  const [jsonMemoryData, setJsonMemoryData] = useState({}); // Initialize an empty JSON object
  const [jsonProcessData, setJsonProcessData] = useState([]);

  useEffect(() => {
    // Fetch data from an API endpoint
    fetch('http://localhost:8080/api/v1/memory')
      .then(response => response.json())
      .then(data => {
        // Update the JSON object with fetched data
        setJsonMemoryData(data);
      })
      .catch(error => {
        console.error('Error fetching memory data:', error);
      });

    // Fetch data from an API endpoint
    fetch('http://localhost:8080/api/v1/memory/top-processes')
      .then(response => response.json())
      .then(data => {
        // Update the JSON object with fetched data
        setJsonProcessData(data);
      })
      .catch(error => {
        console.error('Error fetching memory top process data:', error);
      });
  }, []); // Empty dependency array means this effect runs once after the first render

  // Directly initializing text
  var util = Math.floor(jsonMemoryData.utilization);

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
    <div className="section-Memory">
      <h1 className="title">Memory</h1>

      <div>
        {/* Graph side */}
        <div className="row">
          <img src={graphTemplate} alt="" className="graph" />

          {/* Utilization / Top Processes Side */}
          <div className="col">
            <div className="row">
              <div className="utilBox" style={{ backgroundColor }}> {Math.floor(jsonMemoryData.utilization)}% Utilization</div>
            </div>

            <h4 className="top-processes-table-TITLE"> Top Processes </h4>
            <table className="top-processes-table">
            <tbody>
            {jsonProcessData.length > 0 ? (
                        jsonProcessData.map((process, index) => (
                          <tr key={process.id} className = "top-processes-table-row">
                            <td>{process.name}</td>
                            <td>{(process.memoryUsageBytes / 1000000).toFixed(1)} MB</td>
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

export default MemSection;
