import React, { useEffect, useState } from "react";
import { useFetch } from "../Hooks/useFetch.jsx";
import playlistService, {getAllPlaylists,getPlaylistByName,getAllPlaylistsContainingSong,deletePlaylist,addPlaylist} from "../Services/playlistService";
import {Plus,X} from 'lucide-react';

const Playlists = ({ onPlaylistClick }) => {
const [playlists, setPlaylists] = useState([]);
const { data, loading, error, setData } = useFetch(() => getAllPlaylists(), []);
const [newPlaylistData,setNewPlaylistData]=useState(null);
const [Modal, setModal]=useState(false);
useEffect(() => {
    if (data && Array.isArray(data)) {
      setPlaylists(data);
    }
  }, [data]);

  const handleCreatePlaylist = async () => {
    if(!newPlaylistData || !newPlaylistData.name || !newPlaylistData.user_id) return;
    try {
      const response = await addPlaylist(newPlaylistData.name,newPlaylistData.user_id);
      setPlaylists((prevPlaylists) => [...prevPlaylists, response.data]);
      setModal(false);
      setNewPlaylistData(null);
       
    } catch (err) {
      console.error("Error creating playlist:", err);
    }
  };

  const handleDeletePlaylist=async (playlist_id) =>{
    try{
      await playlistService.deletePlaylist(playlist_id);
      setPlaylists(prev=>prev.filter(p=>p.id !==playlist_id));
    } catch(error)
    {
      console.error("Error deleting playlist",error);
    }
  }

  return (
    <div className="animate-in fade-in duration-500">
      <header className="flex justify-between items-center mb-8">
        <h1 className="text-3xl font-bold">My Playlists</h1>
        <button onClick={() => setModal(true)} className="bg-white text-black px-4 py-2 rounded-full font-bold hover:scale-105 transition-transform">
          <Plus className="w-4 h-4 mr-1 inline" /> Create Playlist
        </button>
      </header>
      {Modal && (
        <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
          <div className="bg-zinc-900 p-6 rounded-lg w-96">
            <h2 className="text-xl font-bold mb-4">Create New Playlist</h2>
            <input
              type="text"
              placeholder="Playlist Name"
              className="w-full mb-4 p-2 rounded bg-zinc-800 text-white"
              onChange={(e) => setNewPlaylistData({ ...newPlaylistData, name: e.target.value })}
            />
            <input
              type="text"
              placeholder="User ID"
              className="w-full mb-4 p-2 rounded bg-zinc-800 text-white"
              onChange={(e) => setNewPlaylistData({ ...newPlaylistData, user_id: e.target.value })}
            />
            <div className="flex justify-end gap-4">
              <button onClick={() => setModal(false)} className="px-4 py-2 rounded bg-zinc-700 hover:bg-zinc-600 transition">Cancel</button>
              <button onClick={handleCreatePlaylist} className="px-4 py-2 rounded bg-violet-600 hover:bg-violet-500 text-white font-bold transition">Create</button>
            </div>
          </div>
        </div>
      )}
      <div className="grid grid-cols-1 md:grid-cols-4 lg:grid-cols-6 gap-4">
        {playlists.map((list) => (
          <div key={list.id} onClick={(e)=>{e.stopPropagation(); onPlaylistClick(list.id);}} className="group bg-zinc-800/40 p-4 rounded-lg hover:bg-zinc-800 transition cursor-pointer relative">
            <div className="aspect-square bg-zinc-700 rounded-md mb-3 flex items-center justify-center shadow-lg" >
              <span className="text-4xl text-zinc-500 absolute">⋆.˚♬⋆.˚</span>
              {list.songs?.[0]?.imageUrl && (<img src={list.songs[0].imageUrl} alt={list.name} className="w-full h-full object-cover relative z-10"
                    onError={(e) => {e.target.style.display = 'none'; }}/> )}            
            </div>
            <h3 className="font-semibold truncate">{list.name}</h3>
            <p className="text-sm text-zinc-400">{list.songs?.length || 0} Şarkı</p>
            <button onClick={(e)=>{e.stopPropagation(); handleDeletePlaylist(list.id);}} className="absolute top-1 right-1 p-1.5 bg-black/40 hover:bg-red-500 text-white rounded-lg opacity-0 group-hover:opacity-100 transition-opacity z-20">
              <X size={16}/>
            </button>
          </div>
        ))}
      </div>
    </div>
  );
};
export default Playlists;