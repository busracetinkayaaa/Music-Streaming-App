import { useEffect,useState } from "react";
import {getAlbumByArtistId,getAlbumByReleaseYear,getAlbumByTitle,getAllAlbums,getRecentAlbumByArtist,createAlbum} from "../Services/albumService" ;

const Albums = ()=>{
    const [albums,setAlbums]=useState(null);  
    useEffect(()=>{
        const fetchAlbums=async ()=>{
            try{
            const response =await getAllAlbums();
            setAlbums(response.data||response);
        }
            catch (error) {
            console.error("Error loading albums", error);
        }
        };
        fetchAlbums();
    },[]);
return (
    <div className="animate-in fade-in duration-500">
        <header className="flex justify-between items-center mb-8">
            <h1 className="text-3xl font-bold">Albums</h1>
        </header>
    <div className="grid grid-cols-2 md:grid-cols-4 lg:grid-cols-6 gap-6 p-4">
    {albums && albums.map((list) => (
        <div key={list.id} className="group bg-zinc-900/40 p-4 rounded-xl hover:bg-zinc-800 transition-colors duration-300 cursor-pointer">
            <div className="aspect-square bg-zinc-800 rounded-lg mb-4 flex items-center justify-center shadow-md overflow-hidden">
                {list.imageUrl ? (
                    <img 
                        src={list.imageUrl} 
                        alt={list.title} 
                        className="w-full h-full object-cover group-hover:scale-105 transition-transform duration-500" 
                    />
                ) : (
                    <span className="text-zinc-600 font-bold text-4xl">
                        {list.title?.charAt(0)}
                    </span>
                )}
            </div>
            <div className="space-y-1">
                <h3 className="text-white font-bold text-sm md:text-base truncate">
                    {list.title}
                </h3>
                <p className="text-zinc-400 text-xs font-medium">
                    {list.artist?.name || "Bilinmeyen Sanatçı"}
                </p>
                
                <div className="flex items-center text-zinc-500 text-[10px] uppercase tracking-wider gap-1">
                    <span>{list.releaseYear}</span>
                    <span>•</span>
                    <span>{list.totalLengthOfAlbum ? `${list.totalLengthOfAlbum} dk` : "Süre Belirtilmedi"}</span>
                </div>
            </div>
        </div>
    ))}
</div>
    </div>        
);
};
export default Albums;