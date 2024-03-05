import logo from './Images/DoTTed Team Logo.png';
import './App.css';

import { Route, Routes} from 'react-router-dom';

// App.js
import React from 'react';
import CpuSection from './Pages/Glance/CpuGlance';
import MemSection from './Pages/Glance/MemoryGlance';
import DiskSection from './Pages/Glance/DiskGlance';
import Processes from './Pages/Processes';
import CpuDetail from './Pages/CpuDetail';
import MemoryDetail from './Pages/MemoryDetail';
import DiskDetail from './Pages/DiskDetail';
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

      <Routes>
        {/* Define routes */}
        <Route path = "/cpu-detail" element={<CpuDetail />} />
        <Route path = "/memory-detail" element={<MemoryDetail />} />
        <Route path = "/disk-detail" element={<DiskDetail />} />
      </Routes>
      
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
