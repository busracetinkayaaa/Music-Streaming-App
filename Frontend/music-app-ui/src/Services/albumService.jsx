import api from "./api.jsx";

export const getAllAlbums=()=>api.get("/albums");
export const createAlbum=(title,releaseYear,artist_id)=>api.post("/albums",{title,releaseYear,artist:{id:artist_id}});
export const getAlbumByTitle=()=>api.get("/albums/search",{params:{title}});
export const getAlbumByReleaseYear=()=>api.get("/albums/release year",{params:{releaseYear}});
export const getAlbumByArtistId=(artist_id)=>api.get('/albums/artist/${artist_id}');
export const getRecentAlbumByArtist=()=>api.get("/albums/Recent albums by artist",{params:{artist_id,year}});

