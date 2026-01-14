import { useState } from 'react';
import { X ,Volume2} from 'lucide-react';
import SearchBar from '../Components/SearchBar.jsx';

const Home = ({ songs, selectedSong, setSelectedSong, isPlaying, deleteSongs ,loading,error}) => {

  const [searchTerm, setSearchTerm] = useState('');

  const filteredSongs = songs.filter((song) =>{
    const titleMatch=song.title.toLowerCase().includes(searchTerm.toLowerCase());
    const artistMatch=song.artist && song.artist.name.toLowerCase().includes(searchTerm.toLowerCase());
    return titleMatch || artistMatch;
  });

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;
  
  const handleDelete=async (id) => {
    if(window.confirm("Are you sure you want to delete this song?")) {
      if(selectedSong && String(selectedSong.id)===String(id)){
        setSelectedSong(null);
      }
      await deleteSongs(id);
    }
  };

  return (
    <div className="animate-in fade-in duration-500">
      <header className="mb-8 flex items-center justify-between">
        <h1 className="text-3xl font-bold"> Explore </h1>
        <SearchBar value={searchTerm} onChange={(e) => setSearchTerm(e.target.value)} />
      </header>
      <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-5 gap-6">
        {filteredSongs.length > 0 ? (
          filteredSongs.map((song) => (
            <div key={song.id} onClick={()=>setSelectedSong(song)} className="bg-zinc-900/40 p-4 rounded-xl hover:bg-zinc-800/60 transition-all group cursor-pointer relative">
              <div className="relative group"> 
                {!song.imageUrl  ? <div className="aspect-square bg-zinc-800 mb-4 rounded-md shadow-lg" /> : <img src={song.imageUrl} alt={song.title} className="aspect-square w-full mb-4 rounded-md shadow-lg object-cover" />}
                {selectedSong?.id === song.id && (
                  <div className="absolute inset-0 aspect-square flex items-center justify-center bg-black/50 rounded-md backdrop-blur-[2px] mb-4">
                    <Volume2 size={32} className={`text-white ${isPlaying ? 'animate-pulse' : 'opacity-50'}`} />
                  </div>
                )}
              </div>      
                  
                  <h3 className="font-bold truncate">{song.title}</h3>
                  <p className="text-zinc-400 text-sm">
                    {song.artist?.name || "Unknown Artist"}
                  </p>
                  <span className="text-xs text-zinc-500">
                    {song.artist?.genre}
                  </span>  
                <button onClick={(e)=>{e.stopPropagation(); handleDelete(song.id);}} className="absolute top-1 right-1 p-1.5 bg-black/40 hover:bg-red-500 text-white rounded-lg opacity-0 group-hover:opacity-100 transition-opacity z-20"> 
                  <X size={16} />
                </button>
           </div>
          ))
        ) : ( 
            <div className="col-span-full text-center py-10 text-zinc-500">
              "No songs found matching {searchTerm}". 
            </div>
            )}
    </div>
    </div>
  );
};
export default Home;