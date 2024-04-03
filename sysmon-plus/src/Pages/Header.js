import logo from '../Images/DoTTed Team Logo.png';

const Header = () => {
    return (
        <header className="App-header">
          <div className="row">
          <img src={logo} alt="" className="App-logo-header" />
            <h1 className="App-title-header"> SysMon+ </h1>
          </div>
        </header>
    )
};

export default Header;