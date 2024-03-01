import React, { useState, useEffect } from 'react';
import graphTemplate from '../../Images/Capping GRAPH TEMPLATE.png'
// import UtilTextBox from '../../../utils/getUtilBackgroundColor';

const CpuSection = () => {

  const [jsonData, setJsonData] = useState({}); // Initialize an empty JSON object
  const [topProcesses, setTopProcesses] = useState([]);

  useEffect(() => {
    // Fetch data from an API endpoint
    fetch('http://localhost:8080/api/v1/cpu')
      .then(response => response.json())
      .then(data => {
        // Update the JSON object with fetched data
        setJsonData(data);
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      });

      // Fetch top 3 processes (based on disk usage) from an API endpoint
      fetch('http://localhost:8080/api/v1/cpu/top-processes')
      .then(response => response.json())
      .then(data => {
        // Update the JSON object with fetched data
        setTopProcesses(data);
      })
      .catch(error => {
        console.error('Error in GET request for disk top processes:', error);
      });
  }, []); // Empty dependency array means this effect runs once after the first render

  // Directly initializing text
  var util = Math.floor(jsonData.utilization);

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
    <div className="section-Cpu">
      <h1 className="title">CPU</h1>
      <h4>{jsonData.name}</h4>

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
                        topProcesses.map((jsonData, index) => (
                          <tr key={jsonData.id} className = "top-processes-table-row">
                            <td>{jsonData.name}</td>
                            <td>{jsonData.cpuPercentage.toFixed(1)}%</td>
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

export default CpuSection;
