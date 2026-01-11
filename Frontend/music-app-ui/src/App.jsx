import React,{ useState } from 'react';
import Player from './Components/Player';
import Sidebar from './Components/Sidebar';
import Home from './Pages/Home';
import Playlists from './Pages/Playlists';

function App() {
  const [activePage, setActivePage] = useState('home');
  const renderContent = () => {
    switch (activePage) {
      case 'home': return <Home />;
      case 'playlists': return <Playlists />;
      case 'search': return <div className="text-white p-10">Arama Sayfası Yapım Aşamasında</div>;
      default: return <Home />;
    }
  };

  return (
    <div className="flex flex-col h-screen bg-black text-white overflow-hidden">
      <div className="flex flex-1 overflow-hidden">
        <Sidebar setPage={setActivePage} />
        <main className="flex-1 overflow-y-auto bg-gradient-to-b from-zinc-900 to-black p-8 mb-24">
           {activePage === 'home' && <Home />}
           {activePage === 'playlists' && <Playlists />}
        </main>
      </div>
      <Player />
    </div>
  );
}

export default App;