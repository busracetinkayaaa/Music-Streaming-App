import api from "./api.jsx";

export const getSongs=()=> api.get("/songs");
export const addSong=(title,duration,artist_id,imageUrl)=> api.post(`/songs`, {
                                                                          title:title,
                                                                          duration:duration,
                                                                          artist_id:artist_id,
                                                                          imageUrl:imageUrl});
export const searchSong=(title)=> api.get('/songs', {params:{title:title}});
export const getLongCollabs=(minDuration)=> api.get("/songs", {params:{minDuration:minDuration}}); 
export const getSongByArtist=(artist_id,name,genre)=> api.get('/songs/', {params:{artist_id:artist_id, name:name, genre:genre}});
export const getSongByAlbumID=(albumId)=> api.get(`/songs/album/${albumId}`);
export const deleteSong=(id)=> api.delete(`/songs/${id}`);

export default {
    getSongs,
    addSong,
    searchSong,
    getLongCollabs,
    getSongByArtist,
    getSongByAlbumID,
    deleteSong
};


