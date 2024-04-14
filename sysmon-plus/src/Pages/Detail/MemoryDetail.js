// memSection.js
import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import MemoryLineChartFromAPI from '../../Charts/MemoryLineChartFromAPI';

const MemoryDetail = () => {

  const [jsonData, setJsonData] = useState({}); // Initialize an empty JSON object

  useEffect(() => {
    // Fetch data from an API endpoint
    const fetchData = () => {
      fetch('http://localhost:8080/api/v1/memory')
        .then(response => response.json())
        .then(data => {
          // Update the JSON object with fetched data
          setJsonData(data);
        })
        .catch(error => {
          console.error('Error fetching memory data:', error);
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

  const divStyle = {
    display: 'contents'
  };

  const buttonStyle = {
    textAlign: 'left'
  };

  return (
    <div className="section-Mem">
      <h1 className='detail-title'> Memory </h1>
      <h4 className='detail-title'></h4>

      <div>
        {/* Graph side */}
        <div className="detail-row">
          {<MemoryLineChartFromAPI view='detail' />}

          {/* Utilization / Top Processes Side */}
          <div className="utilandTopProc-sec-detail">
            <Link to="/"> <button className="back-button"> &#10096; BACK </button> </Link>

            <div style={divStyle}>
              <div className="utilBox-detail" style={{ backgroundColor }}> {util}% Utilization </div>
            </div>

            <table className="detail-table">
              <caption className='detail-table-TITLE'> Memory Info </caption>
              <tbody>
                <tr>
                  <td> Total Memory: </td>
                  <td>{Math.floor(jsonData.totalMemory / 1000000)} MB</td>
                </tr>

                <tr>
                  <td> Available Memory: </td>
                  <td> {Math.floor(jsonData.availableMemory/ 1000000)} MB</td>
                </tr>

                <tr>
                  <td> Used Memory: </td>
                  <td> {Math.floor(jsonData.usedMemory / 1000000)} MB</td>
                </tr>

                {/* <tr>
                  <td> Utilization: </td>
                  <td> {(jsonData.utilization).toFixed(1)}%</td>
                </tr> */}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
};

export default MemoryDetail;