// cpuSection.js
import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import CpuLineChartFromAPI from '../../Charts/CpuLineChartFromAPI';

const CpuDetail = () => {

  const [jsonData, setJsonData] = useState({}); // Initialize an empty JSON object

  // const navigate = useNavigate();

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
  

  return (
    <div className="section-Cpu">
      

      <h1 className='detail-title'> CPU </h1>
      <h4 className='detail-title'>{jsonData.name}</h4>

      <div>
        {/* Graph side */}
        <div className="detail-row">
          <CpuLineChartFromAPI view="detail"/>

          {/* Utilization / Top Processes Side */}
          <div className="utilandTopProc-sec-detail">
            <Link to="/"> <button className="back-button"> &#10096; BACK </button> </Link>

            <div style={divStyle}>
              <div className="utilBox-detail" style={{ backgroundColor }}> {util}% Utilization </div>
            </div>

            <table className="detail-table">
              <caption className='detail-table-TITLE'> Processor Info </caption>
              <tbody>
                <tr>
                  <td> Processor Speed: </td>
                  <td>{(jsonData.speed / 1000000000).toFixed(2)} GHz</td>
                </tr>

                <tr>
                  <td> Processor Max Speed: </td>
                  <td> {(jsonData.maxSpeed/ 1000000000).toFixed(2)} GHz</td>
                </tr>

                <tr>
                  <td> Cores: </td>
                  <td> {jsonData.numCores} </td>
                </tr>

                <tr>
                  <td> Processes: </td>
                  <td> {jsonData.numProcesses} </td>
                </tr>

                <tr> 
                  <td> Threads: </td>
                  <td> {jsonData.numThreads} </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CpuDetail;
