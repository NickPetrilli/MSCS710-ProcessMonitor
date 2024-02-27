import React from 'react';

const Processes = () => {

  return (
    <div className="Processes">
        <h1>Processes</h1>
        
        <div>
            <table className="processes-table">
                <thead>
                    <tr className='process-table-row'>
                        <th> Name </th>
                        <th> CPU </th>
                        <th> Memory </th>
                        <th> Disk </th>
                    </tr>
                </thead>
                <tbody>
                    <tr className='process-row'>
                        <th> Firefox </th>
                        <th> 13.2% </th>
                        <th> 2,146.2 MB </th>
                        <th> 0.0 Mb/s </th>
                        </tr>

                    <tr className='process-row'>
                        <th> Avast Antivirus </th>
                        <th> 1.1% </th>
                        <th> 114.4 MB </th>
                        <th> 0.0 Mb/s </th>
                    </tr>
                    
                    <tr className='process-row'>
                        <th> Microsoft Edge </th>
                        <th> 0.8% </th>
                        <th> 475.3 MB </th>
                        <th> 0.0 Mb/s </th>
                    </tr>

                    <tr className='process-row'>
                        <th> NVIDIA Control Panel </th>
                        <th> 0.0% </th>
                        <th> 5.6 MB </th>
                        <th> 0.0 Mb/s </th>
                    </tr>

                    <tr className='process-row'>
                        <th> NVIDIA Controller </th>
                        <th> 0.0% </th>
                        <th> 9.8 MB </th>
                        <th> 0.0 Mb/s </th>
                    </tr>

                    <tr className='process-row'>
                        <th> Runtime Broker </th>
                        <th> 0.0% </th>
                        <th> 19.7 MB </th>
                        <th> 0.0 Mb/s </th>
                    </tr>

                    <tr className='process-row'>
                        <th> AMD Software </th>
                        <th> 0.0% </th>
                        <th> 6.2 MB </th>
                        <th> 0.0 Mb/s </th>
                    </tr>

                    <tr className='process-row'>
                        <th> Notepad </th>
                        <th> 0.0% </th>
                        <th> 1.1 MB </th>
                        <th> 0.0 Mb/s </th>
                    </tr>

                    <tr className='process-row'>
                        <th> GNU Manipulation </th>
                        <th> 0.0% </th>
                        <th> 5.3 MB </th>
                        <th> 0.0 Mb/s </th>
                    </tr>

                    <tr className='process-row'>
                        <th> Office Click-N-Run </th>
                        <th> 0.0% </th>
                        <th> 4.0 MB </th>
                        <th> 0.0 Mb/s </th>
                    </tr>

                    <tr className='process-row'>
                        <th> NordVPN </th>
                        <th> 0.0% </th>
                        <th> 26.2 MB </th>
                        <th> 0.0 Mb/s </th>
                    </tr>

                    <tr className='process-row'>
                        <th> Gaming Services </th>
                        <th> 0.0% </th>
                        <th> 11.9 MB </th>
                        <th> 0.0 Mb/s </th>
                    </tr>

                    <tr className='process-row'>
                        <th> f.lux </th>
                        <th> 0.0% </th>
                        <th> 1.3 MB </th>
                        <th> 0.0 Mb/s </th>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
  );
};

export default Processes;
