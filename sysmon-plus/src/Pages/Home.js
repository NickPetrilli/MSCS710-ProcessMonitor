import React from 'react';
import logo from '../Images/DoTTed Team Logo.png';

import CpuSection from './Glance/CpuGlance';
import MemSection from './Glance/MemoryGlance';
import DiskSection from './Glance/DiskGlance';
import Processes from './Processes';


const Home = () => {
    return (
      <div className="App">
        <header className="App-header">
          <div className="row">
          <img src={logo} alt="" className="App-logo-header" />
            <h1 className="App-title-header"> SysMon+ </h1>
          </div>
        </header>
        
        <div>
          <div className="row">
            <CpuSection />
            <MemSection />
          </div>
        </div>
  
        <hr></hr>
  
        <div className="center-container">
          <DiskSection />
        </div>
  
        <hr></hr>
  
        <div>
          <Processes />
        </div>
  
        <hr></hr>
  
        <div>
          
        </div>
  
      </div>
    );
  };

  export default Home;