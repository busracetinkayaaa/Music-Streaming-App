import api from "./api.jsx";

export const getAllPlaylists=()=> api.get("/playlists");
export const addPlaylist=(name,user_id)=> api.post(`/playlists`, { name:name,user_id:user_id});
export const deletePlaylist=()=> api.delete(`/playlists/${playlist_id}`);
export const deleteSongFromPlaylist=()=> api.delete(`/playlists/${playlist_id}/delete-song/${song_id}`);
export const addSongToPlaylist=(playlist_id,song_id)=> api.put(`/playlists/${playlist_id}/add-song/${song_id}`);
export const getAllPlaylistsContainingSong=()=> api.get(`/playlists/song/${song_id}`,{song_id:song_id});
export const getPlaylistByName=(name)=> api.get(`/playlists/name`, {params:{name:name}});
export const getPlaylistDetails=(id)=> api.get(`/playlists/details/${id}`);

export default {
    getAllPlaylists,
    addPlaylist,
    deletePlaylist,
    deleteSongFromPlaylist,
    addSongToPlaylist,
    getAllPlaylistsContainingSong,
    getPlaylistByName,
    getPlaylistDetails
}; 