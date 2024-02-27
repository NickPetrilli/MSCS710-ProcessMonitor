import logo from './Images/DoTTed Team Logo.png';
import './App.css';

// App.js
import React from 'react';
import CpuSection from './Pages/Glance/CpuGlance';
import MemSection from './Pages/Glance/MemoryGlance';
import DiskSection from './Pages/Glance/DiskGlance';
import Processes from './Pages/Processes';
import './App.css';

const App = () => {
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

export default App;
