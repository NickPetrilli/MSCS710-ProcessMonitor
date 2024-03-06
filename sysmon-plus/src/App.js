import './App.css';

// App.js
import React from 'react';
import { Route, Routes} from 'react-router-dom';
import Home from './Pages/Home';
import CpuDetail from './Pages/Detail/CpuDetail';
import MemoryDetail from './Pages/Detail/MemoryDetail';
import DiskDetail from './Pages/Detail/DiskDetail';
import Processes from './Pages/Processes';
import { CpuProcesses } from './Pages/Processes';
import { MemoryProcesses } from './Pages/Processes';
import { DiskProcesses } from './Pages/Processes';
import './App.css';

const App = () => {
  return (
      <div className="App">
        <Routes>
          {/* Define routes */}
          <Route path = "/" exact element={<Home />} />
          <Route path = "/cpu-detail" element={<CpuDetail />} />
          <Route path = "/memory-detail" element={<MemoryDetail />} />
          <Route path = "/disk-detail" element={<DiskDetail />} />
          <Route path = "/processes" element={<Processes />} />
          <Route path = "/cpu-processes" element={<CpuProcesses />} />
          <Route path = "/memory-processes" element={<MemoryProcesses />} />
          <Route path = "/disk-processes" element={<DiskProcesses />} />
        </Routes>
      </div>
    );
};

export default App;
