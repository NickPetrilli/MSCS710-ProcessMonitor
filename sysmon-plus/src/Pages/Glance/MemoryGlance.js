// memSection.js
import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import MemoryLineChartFromAPI from '../../Charts/MemoryLineChartFromAPI';

const MemSection = () => {

  const rowStyle = {
    paddingTop: '7.25%'
  };
  const MAX_PROC_LENGTH = 7;  

  const [jsonMemoryData, setJsonMemoryData] = useState({}); // Initialize an empty JSON object
  const [jsonProcessData, setJsonProcessData] = useState([]);

  useEffect(() => {
    // Fetch data from an API endpoint
    const fetchData = () => {
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
    }

    // Run the function immediately when the component mounts
    fetchData();

    // Set up interval to run the function every 10 seconds
    const intervalId = setInterval(fetchData, 10000);
      
    return () => clearInterval(intervalId);
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

  function truncateString(str, maxLength) {
    if (str.length <= maxLength) {
      return str;
    } else {
      return str.substring(0, maxLength + 1) + '...';
    }
  }


  return (
    <div className="section-Mem">
      <Link to="/memory-detail"> <h1 className='section-title'> Memory </h1> </Link> 

      <div>
        {/* Graph side */}
        <div className="glance-row" style={rowStyle}>
          <MemoryLineChartFromAPI />

          {/* Utilization / Top Processes Side */}
          <div className="utilandTopProc-sec">
            <div className="row">
              <Link to="/memory-detail"> <div className="utilBox-glance" style={{ backgroundColor }}> {util}% Utilization</div> </Link>
            </div>

            <table className="top-processes-table">
              <Link to="/memory-processes"> <caption className="top-processes-table-TITLE"> Top Processes </caption> </Link>
            <tbody>
            {jsonProcessData.length > 0 ? (
                        jsonProcessData.map((process, index) => (
                          <tr key={process.id} className = "top-processes-table-row">
                            <td className='top-processes-table-proc'>{truncateString(process.name, MAX_PROC_LENGTH)}</td>
                            <td className='top-processes-table-util'>{(process.memoryUsageBytes / 1000000).toFixed(1)} MB</td>
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
