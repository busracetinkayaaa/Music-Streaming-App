import { useState } from 'react';
import {useSongs} from '../Hooks/useSongs.jsx';
import Player from '../Components/Player.jsx';


const Home = () => {
  const [selectedSong, setSelectedSong] = useState(null);
  const { songs, loading, error } = useSongs();


  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;
  

  return (
    <div className="animate-in fade-in duration-500">
      <header className="mb-8">
        <h1 className="text-3xl font-bold"> Explore </h1>
      </header>
      <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-5 gap-6">
        {songs.map((song) => (
          <div key={song.id} onClick={()=>setSelectedSong(song)} className="bg-zinc-900/40 p-4 rounded-xl hover:bg-zinc-800/60 transition-all group cursor-pointer">
            
            {!song.imageUrl  ? <div className="aspect-square bg-zinc-800 mb-4 rounded-md shadow-lg" /> : <img src={song.imageUrl} alt={song.title} className="aspect-square w-full mb-4 rounded-md shadow-lg object-cover" />}

                <h3 className="font-bold truncate">{song.title}</h3>
                
                <p className="text-zinc-400 text-sm">
                  {song.artist?.name || "Unknown Artist"}
                </p>

                <span className="text-xs text-zinc-500">
                  {song.artist?.genre}
                </span>             
              </div>
            ))}

      </div>
        {selectedSong && (
        <Player songs={songs} selectedSong={selectedSong} setSelectedSong={setSelectedSong} />     
        )}
     </div>
  );
};
export default Home;