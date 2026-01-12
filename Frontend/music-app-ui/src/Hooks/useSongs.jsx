import {useState,useEffect} from 'react';
import {getSongs,searchSong,deleteSong,addSong,getLongCollabs,getSongByAlbumID,getSongByArtist} from '../Services/songService.jsx';   
import { useFetch } from './useFetch.jsx';

export function useSongs() {
    const [songs, setSongs] = useState([]);
    const {data,loading,error} = useFetch(()=>getSongs(), []);

    useEffect(() => {
        if(data&&Array.isArray(data)){
            setSongs(data);
        }
    }, [data]);

    const deleteSongs=async (id) => {
        try {
            await deleteSong(id);
            setSongs((prevSongs) => prevSongs.filter((song) => String(song.id) !== String(id)));
        } catch (err) {
            console.error("Error deleting song:", err);
        }
    };
    return { songs, loading, error,deleteSongs};

        
} 