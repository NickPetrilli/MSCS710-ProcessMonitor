import React, { useState, useEffect, useMemo } from 'react';
import { Link } from 'react-router-dom';
import DiskLineChartFromAPI from '../../Charts/DiskLineChartFromAPI';

const DiskSection = () => {
  const [disks, setDisks] = useState([]);
  const [selectedDiskName, setSelectedDiskName] = useState('');
  const [topProcesses, setTopProcesses] = useState([]);

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

        // Fetch top processes data
        const processesResponse = await fetch('http://localhost:8080/api/v1/disk/top-processes');
        const processData = await processesResponse.json();
        console.log('Fetched top processes:', processData); // Debugging
        setTopProcesses(processData);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();

    // Set up interval to run the function every 10 seconds
    const intervalId = setInterval(fetchData, 10000);

    return () => clearInterval(intervalId);
  }, []);

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

  // Memoize the selection of the disk to ensure it's recalculated only when necessary
  const selectedDisk = useMemo(() => disks.find(disk => disk.name === selectedDiskName), [disks, selectedDiskName]);
  console.log('Selected disk details:', selectedDisk); // Debugging

  return (
    <div className="section">
      <Link to={{
            pathname: "/disk-detail"
      }}>
        <h1>Disk</h1>
      </Link>
      {disks.length > 0 && (
        <select onChange={handleDiskChange} value={selectedDiskName}>
          {disks.map((diskItem) => (
            <option key={diskItem.name} value={diskItem.name}>{diskItem.model} - {diskItem.name}</option>
          ))}
        </select>
      )}

      <div className="glance-row">
        {selectedDiskName && <DiskLineChartFromAPI key={selectedDiskName} diskName={selectedDiskName} view='glance' />}
        {!selectedDiskName && <p>Select a disk to view its details.</p>}

        <div className="utilandTopProc-sec">
          <div className="row">
            <Link to={{
                  pathname: "/disk-detail"
              }}>
              {selectedDisk ? (
                <div className="utilBox-glance" style={{backgroundColor: selectedDisk.utilization < 40 ? 'green' : selectedDisk.utilization < 80 ? 'orange' : 'red'}}>
                  {selectedDisk.utilization.toFixed(5)}% Utilization
                </div>
              ) : (
                <p>No data available</p>
              )}
            </Link>
          </div>

          <table className="top-processes-table">
            <Link to="/cpu-processes"><caption className="top-processes-table-TITLE">Top Processes</caption></Link>
            <tbody>
              {topProcesses.length > 0 ? (
                topProcesses.map((item) => (
                  <tr key={item.id} className="top-processes-table-row">
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
  );
};

export default DiskSection;
