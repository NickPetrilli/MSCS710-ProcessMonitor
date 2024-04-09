// diskSection.js
import React, { useState, useEffect, useMemo } from 'react';
import { Link } from 'react-router-dom';
import DiskLineChartFromAPI from '../../Charts/DiskLineChartFromAPI';

const DiskDetail = () => {

  const [disks, setDisks] = useState([]);
  const [selectedDiskName, setSelectedDiskName] = useState(() => sessionStorage.getItem('selectedDiskName'));

  useEffect(() => {
    const fetchData = async () => {
      try {
        // Fetch disks data
        const diskResponse = await fetch('http://localhost:8080/api/v1/disk');
        const diskData = await diskResponse.json();
        console.log('Fetched disks:', diskData); // Debugging
        setDisks(diskData);

        // Automatically select the first disk or restore selection from session storage
        const initialDiskName = sessionStorage.getItem('selectedDiskName') || (diskData.length > 0 ? diskData[0].name : '');
        setSelectedDiskName(initialDiskName);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    }

  // Run the function immediately when the component mounts
  fetchData();

  // Set up interval to run the function every 10 seconds
  const intervalId = setInterval(fetchData, 10000);

  return () => clearInterval(intervalId);
  }, []); // Empty dependency array means this effect runs once after the first render

  useEffect(() => {
    if (selectedDiskName) {
      sessionStorage.setItem('selectedDiskName', selectedDiskName);
      console.log('Selected disk name saved to session:', selectedDiskName); // Debugging
    }
  }, [selectedDiskName]);

  const handleDiskChange = (event) => {
    console.log('Disk selection changed:', event.target.value); // Debugging
    setSelectedDiskName(event.target.value);
  };

  const selectedDisk = useMemo(() => disks.find(disk => disk.name === selectedDiskName), [disks, selectedDiskName]);
  // console.log('Selected disk details:', selectedDisk.name); // Debugging

  const divStyle = {
    display: 'contents'
  };

  const buttonStyle = {
    textAlign: 'left'
  };

  return (
    <div className="section-Disk">
      <Link to="/"> <button className="back-button" style={buttonStyle}> BACK </button> </Link>
      
      <h1 className='detail-title'> Disk </h1>
      <h4 className='section-title' style={divStyle}>{disks.length > 0 && (
        <select className='disk-selection-menu' onChange={handleDiskChange} value={selectedDiskName}>
          {disks.map((diskItem) => (
            <option className='disk-selection-option' key={diskItem.name} value={diskItem.name}>{diskItem.model} - {diskItem.name}</option>
          ))}
        </select>
      )} </h4>

      {
        selectedDisk ? (
        <div>
          {/* Graph side */}
          <div className="detail-row">
            {selectedDiskName && <DiskLineChartFromAPI diskName={selectedDisk.name} view='detail' />}

            {/* Utilization / Top Processes Side */}
            <div className="utilandTopProc-sec-detail">
              <div style={divStyle}>
                {selectedDisk ? (
                  <div className="utilBox-glance" style={{backgroundColor: selectedDisk.utilization < 40 ? 'green' : selectedDisk.utilization < 80 ? 'orange' : 'red'}}>
                    {Math.floor(selectedDisk.utilization)}% Utilization
                  </div>
                ) : (
                  <p>No data available</p>
                )}
              </div>

              <table className="detail-table">
                <caption className='detail-table-TITLE'> Disk Info </caption>
                <tbody>
                  <tr>
                    <td> Read Speed: </td>
                    <td>{(selectedDisk.readSpeed / 1000).toFixed(1)} KB/s</td>
                  </tr>

                  <tr>
                    <td> Write Speed: </td>
                    <td> {(selectedDisk.writeSpeed / 1000).toFixed(1)} KB/s</td>
                  </tr>

                  <tr>
                    <td> Swap Used: </td>
                    <td> {(selectedDisk.swapUsed / 1000000).toFixed(1)} MB</td>
                  </tr>

                  <tr>
                    <td> Swap Available: </td>
                    <td> {(selectedDisk.swapAvailable / 1000000).toFixed(1)} MB</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
          
        ) : (
          <p>No disk selected</p>
        )
      }
      
    </div>
  );
};

export default DiskDetail;
