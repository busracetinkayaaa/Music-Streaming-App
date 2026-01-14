import {useEffect,useState,useCallback} from "react";
import playlistService from "../Services/playlistService";
import songService from "../Services/songService";
import SearchBar from "../Components/SearchBar";
import {X} from "lucide-react";

const PlaylistDetail = ({ playlist_id,setSelectedSong }) => {
    const [playlist, setPlaylist] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [searchTerm, setSearchTerm] = useState('');
    const [searchResults, setSearchResults] = useState([]);
    const fetchDetails = useCallback(async () => {
            if (!playlist_id) return;
            
            try {
                if (!playlist) setLoading(true);
                console.log("İstek atılıyor, Playlist ID:", playlist_id);
                const response = await playlistService.getPlaylistDetails(playlist_id);
                console.log("Gelen Veri:", response.data);
                setPlaylist(response.data);
                setError(null);
            } catch (err) {
                console.error("Veri çekme hatası:", err);
                setError("Çalma listesi yüklenemedi.");
            } finally {
                setLoading(false);
            }
            }, [playlist_id]);

    useEffect(() => {
        fetchDetails();
        },[fetchDetails]);
    
    const handleSearch = async (term) => {
        setSearchTerm(term);    
        if(term.length > 2 ){
            console.log("Şu kelime aranıyor:", term); 
            const response = await songService.searchSong(term);
            console.log("Arama sonuçları:", response.data); 
            const results = Array.isArray(response.data) 
                ? response.data 
                : [response.data];
            setSearchResults(results);    
        } else {
            setSearchResults([]);
        }
    };

    const handleAddSongToPlaylist = async (song_id) => {
        try {
            await playlistService.addSongToPlaylist(playlist_id,song_id);
            await fetchDetails();
            setSearchTerm('');
            setSearchResults([]);
        }
        catch (err) {
            console.error("Error adding song to playlist:", err);
        }
    };
    if (loading) return <div className="p-10 text-white animate-pulse">Yükleniyor...</div>;
    if (error) return <div className="p-10 text-red-500">{error}</div>;
    if (!playlist) return <div className="p-10 text-white">Playlist bulunamadı.</div>;   

    const handleDeleteSongFromPlaylist=async(song_id)=>{
        try{
            await playlistService.deleteSongFromPlaylist(playlist_id,song_id);
            await fetchDetails();
        }catch(error)
        {
            console.error("failed deleting song",error);
        }
    };

    return (
        <div className="p-8 bg-zinc-950 text-white min-h-screen">
            <header className="flex justify-between items-start pl-6 mb-16 gap-12">
                <div className="flex-1">
                    <h1 className="text-5xl font-black mb-10"> {playlist.name} </h1>
                    <p className="text-zinc-400">{playlist.songs?.length} Şarkı - {playlist.formattedDuration}</p>                
                </div>
            
                <div className="w-full max-w-sm flex flex-col justify-center pr-6 ">   
                    <h2 className="text-2xl font-bold mb-6 text-center w-full">Add Songs to Playlist</h2>
                    <div className="relative">
                        <SearchBar value={searchTerm} onChange={(e) => handleSearch(e.target.value)} />    

                    {searchResults.length > 0 && (
                        <div className="absolute top-full left-0 right-0 mt-2 bg-zinc-900 rounded-xl overflow-hidden border border-zinc-700 shadow-2xl z-50 max-h-96 overflow-y-auto">
                            {searchResults.map((song) => (
                                <div key={song.id} className="flex justify-between items-center p-3 hover:bg-zinc-800 border-b border-zinc-800 last:border-0 transition-colors">
                                    <div className="min-w-0 mr-4">  
                                        <p className="font-medium">{song.title}</p>
                                        <p className="text-sm text-zinc-400">{song.artist?.name}</p> 
                                    </div>
                                    <button onClick={()=>handleAddSongToPlaylist(song.id)} className="border border-zinc-500 hover:border-white px-4 py-1 rounded-full text-sm font-bold transition">
                                        Add to Playlist 
                                    </button>
                                </div>
                            ))}        
                        </div>   
                    )}
                    </div>
                </div>        
            </header>
                                
            <div className="mb-12">
            {playlist.songs?.map((song,index)=>(
                <div key={song.id} onClick={()=>setSelectedSong(song)} className="group flex items-center gap-4 p-3 hover:bg-zinc-800/60 rounded-lg transition cursor-pointer relative">
                    <span className="text-zinc-500 ext-sm font-medium w-6 text-center">{index + 1}</span>
                        <div className="shrink-0">
                           <img src={song.imageUrl} alt={song.title} className="h-12 w-12 rounded shadow-md object-cover" />                           
                        </div> 
                        <div className="flex-1 min-w-0">
                            <p className="font-medium text-white truncate">
                                {song.title}
                            </p>
                            <p className="text-sm text-zinc-400 truncate">
                                {song.artist?.name}
                            </p>
                        </div>
                        <button className="p-1.5 bg-black/40 hover:bg-red-500 text-white rounded-lg opacity-0 group-hover:opacity-100 transition-opacity z-20" onClick={(e)=>{e.stopPropagation(); handleDeleteSongFromPlaylist(song.id)}}>
                            <X size={16}/>
                        </button>   

               </div>
            ))}
            </div>  
        </div>
    );
};
export default PlaylistDetail;