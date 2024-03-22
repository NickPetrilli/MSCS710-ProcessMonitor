// cpuSection.js
import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import logo from '../../Images/DoTTed Team Logo.png';
import CpuLineChartFromAPI from '../../Charts/CpuLineChartFromAPI';

const CpuDetail = () => {

  const [jsonData, setJsonData] = useState({}); // Initialize an empty JSON object

  const navigate = useNavigate();

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

  const goBack = () => {
    navigate(-1);
  };

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
      <h1> CPU </h1>
      <h4>{jsonData.name}</h4>

      <div>
        {/* Graph side */}
        <div className="glance-row">
          <CpuLineChartFromAPI />

          {/* Utilization / Top Processes Side */}
          <div className="utilandTopProc-sec">
            <div className="row">
              <Link to="/cpu-detail"> <div className="utilBox-glance" style={{ backgroundColor }}> {util}% Utilization </div> </Link>
            </div>

            <table className="glance-table">
              <caption> Processor Info </caption>
              <tbody>
                <tr>
                  <td> Processor Speed: </td>
                  <td>{jsonData.speed}</td>
                </tr>

                <tr>
                  <td> Processor Max Speed: </td>
                  <td> {jsonData.maxSpeed}</td>
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

                <tr> 
                  <td> Utilization: </td>
                  <td> {jsonData.utilization} </td>
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
