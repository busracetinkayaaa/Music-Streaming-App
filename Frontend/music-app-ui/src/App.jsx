import React,{ useState } from 'react';
import Player from './Components/Player';
import Sidebar from './Components/Sidebar';
import Home from './Pages/Home';
import Playlists from './Pages/Playlists';
import { useSongs } from './Hooks/useSongs.jsx';
import PlaylistDetail from './Pages/PlaylistDetail.jsx';

function App() {
  const [activePage, setActivePage] = useState('home');
  const [selectedSong, setSelectedSong] = useState(null);
  const [isPlaying, setIsPlaying] = useState(false);
  const [selectedPlaylistId, setSelectedPlaylistId] = useState(null);

  const { songs, loading, error,deleteSongs } = useSongs();

  const handleOpenPlaylist = (playlist_id) => {
    setSelectedPlaylistId(playlist_id);
    setActivePage('playlistDetail');
  }

  const renderContent = () => {
    switch (activePage) {
      case 'home': return <Home 
        songs={songs}
        selectedSong={selectedSong}
        setSelectedSong={setSelectedSong}
        isPlaying={isPlaying}
        deleteSongs={deleteSongs}
        loading={loading}
        error={error}
      />;
      case 'playlists': return <Playlists onPlaylistClick={handleOpenPlaylist}/>;
      case 'playlistDetail': return <PlaylistDetail playlist_id={selectedPlaylistId} selectedSong={setSelectedSong} />;
      case 'search': return <div className="text-white p-10">Arama Sayfası Yapım Aşamasında</div>;
      default: return <Home />;
    }
  };

  return (
    <div className="flex flex-col h-screen bg-black text-white overflow-hidden">
      <div className="flex flex-1 overflow-hidden">
        <Sidebar setPage={setActivePage} activePage={activePage} />
        <main className="flex-1 overflow-y-auto bg-linear-to-b from-zinc-900 to-black p-8 mb-24">
          {renderContent()}
        </main>
      </div>
      {selectedSong &&( <Player  
        songs={songs}
        selectedSong={selectedSong}
        setSelectedSong={setSelectedSong}
        isPlaying={isPlaying}
        setIsPlaying={setIsPlaying}
       />)}
    </div>
  );
}

export default App;