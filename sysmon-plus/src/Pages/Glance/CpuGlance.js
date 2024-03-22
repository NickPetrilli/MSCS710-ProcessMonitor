import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
// import UtilTextBox from '../../../utils/getUtilBackgroundColor';
import CpuGlanceLineChartFromAPI from '../../Charts/CpuGlanceLineChartFromAPI';

const CpuSection = () => {

  const [jsonData, setJsonData] = useState({}); // Initialize an empty JSON object
  const [topProcesses, setTopProcesses] = useState([]);

  useEffect(() => {
    // Fetch data from an API endpoint
    const fetchData = () => {
      fetch('http://localhost:8080/api/v1/cpu')
        .then(response => response.json())
        .then(data => {
          // Update the JSON object with fetched data
          setJsonData(data);
        })
        .catch(error => {
          console.error('Error fetching data:', error);
        });

      // Fetch top 3 processes (based on cpu usage) from an API endpoint
      fetch('http://localhost:8080/api/v1/cpu/top-processes')
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
      <Link to="/cpu-detail"> <h1> CPU </h1> </Link> 
      <Link to="/cpu-detail"> <h4>{jsonData.name}</h4> </Link>

      <div>
        {/* Graph side */}
        <div className="glance-row">
          <CpuGlanceLineChartFromAPI />

          {/* Utilization / Top Processes Side */}
          <div className="utilandTopProc-sec">
            <div className="row">
              <Link to="/cpu-detail"> <div className="utilBox" style={{ backgroundColor }}> {util}% Utilization </div> </Link>
            </div>

            <table className="top-processes-table">
              <Link to="/cpu-processes"> <caption className="top-processes-table-TITLE"> Top Processes </caption> </Link>
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
