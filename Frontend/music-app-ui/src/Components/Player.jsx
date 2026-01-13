import { Play, SkipBack, SkipForward, Volume2, Repeat, Shuffle, Pause } from 'lucide-react';
import { useEffect, useRef, useState } from "react";

const Player = ({ songs,selectedSong ,setSelectedSong, isPlaying, setIsPlaying}) => {
  const audioRef = useRef(null);
  const [volume, setVolume] = useState(50); 
  const [currentIndex, setCurrentIndex] = useState(0);
  const [isRepeat, setIsRepeat] = useState(false);
  const songUrl = "/src/testMusic.mp3";
  const [currentTime, setCurrentTime] = useState(0);
  const [duration, setDuration] = useState(0);
  const progressPercent = duration ? (currentTime / duration) * 100 : 0;

  if(!selectedSong) return null;

  const togglePlay=async() => {
    if(!audioRef.current) return;
    if(isPlaying){
      audioRef.current.pause();
      setIsPlaying(false);
    }
    else{
      try {
        await audioRef.current.play();
        setIsPlaying(true);
      } catch (error) {
        console.error("Error playing audio:", error.message);
    } 
  }
  };
  const nextSong = () => {
    const nextIndex = (currentIndex + 1) % songs.length;
    setCurrentIndex(nextIndex);
    setSelectedSong(songs[nextIndex]);
    setIsPlaying(true);
  };
  const prevSong = () => {
    const prevIndex = (currentIndex - 1 + songs.length) % songs.length;
    setCurrentIndex(prevIndex);
    setSelectedSong(songs[prevIndex]);
    setIsPlaying(true);
  };
  const shuffleSongs = () => {
    const randomIndex = Math.floor(Math.random() * songs.length);
    setCurrentIndex(randomIndex);
    setSelectedSong(songs[randomIndex]);
    setIsPlaying(true);
  };

  const formatTime = (time) => {
    if(isNaN(time)) return "0:00";
    const minutes = Math.floor(time / 60);
    const seconds = Math.floor(time % 60).toString().padStart(2, '0');
    return `${minutes}:${seconds}`;
  };

  const handleVolumeChange = (e) => {
    const value = e.target.value;
    setVolume(value);
    if (audioRef.current) {
      audioRef.current.volume = value / 100; 
    }
  };

  const handleClose = () => {
    if (audioRef.current) {
        audioRef.current.pause();
        audioRef.current.currentTime = 0;
    }
    setIsPlaying(false);
    setCurrentTime(0);
    setSelectedSong(null);
  };

  useEffect(() => {
    if(selectedSong&& songs[currentIndex]?.id !== selectedSong.id){
      const index = songs.findIndex((song) => song.id === selectedSong.id);
      
        if (index !== -1) {
      setCurrentIndex(index);
      setIsPlaying(true);
      }

      if(audioRef.current?.duration){
        setDuration(audioRef.current.duration);
      }
      else if(selectedSong?.duration){
        setDuration(selectedSong.duration);}
    }
 
  }, [selectedSong,songs]);

  useEffect(() => {
    const playAudio = async () => {
      if (audioRef.current && isPlaying) {
        try {
          audioRef.current.load();
          await audioRef.current.play();
        } catch (error) {
          console.error("Error playing audio:", error.message);
        }
      }
    };
    playAudio();

  }, [currentIndex]);
    const currentActiveSong = songs[currentIndex] || selectedSong;

  return (
    <div className="fixed bottom-0 left-0 right-0 h-24 bg-black border-t border-zinc-900 px-4 flex items-center justify-between z-50">
      <button 
        onClick={handleClose}
        className="absolute top-2 right-3 text-zinc-400 hover:text-white text-xl">
        ✕
      </button>

      <div className="flex items-center gap-4 w-1/3">
        <div className="w-14 h-14 bg-zinc-800 rounded-md shadow-lg" >
        {!currentActiveSong?.imageUrl  ? <div className="aspect-square bg-zinc-800 mb-4 rounded-md shadow-lg" /> : <img src={currentActiveSong?.imageUrl} alt={currentActiveSong?.title} className="aspect-square w-full mb-4 rounded-md shadow-lg object-cover" />}
          </div>
        <div className="hidden sm:block">
          <h4 className="text-sm font-semibold text-white">{currentActiveSong?.title || "Şarkı İsmi"}</h4>
          <p className="text-xs text-zinc-400">{currentActiveSong?.artist?.name || "Sanatçı"}</p>
        </div>
      </div>

      <div className="flex flex-col items-center gap-2 max-w-[45%] w-full">
        <div className="flex items-center gap-6">
          <Shuffle size={18} onClick={shuffleSongs} className="text-zinc-400 hover:text-white cursor-pointer" />
          <SkipBack size={24} onClick={prevSong} className="text-zinc-400 hover:text-white fill-zinc-400 cursor-pointer" />
          <button onClick={togglePlay} className="bg-white rounded-full p-2 hover:scale-105 transition">
            {isPlaying ? <Pause size={24} className="text-black fill-black" /> : <Play size={24} className="text-black fill-black" />}
          </button>
          <SkipForward size={24} onClick={nextSong} className="text-zinc-400 hover:text-white fill-zinc-400 cursor-pointer" />
          <Repeat size={18} onClick={() => setIsRepeat(!isRepeat)} className={isRepeat ? "text-green-500" : "text-zinc-400"}/>
        </div>

        <div className="flex items-center gap-2 w-full">
          <span className="text-[10px] text-zinc-400">{formatTime(currentTime)}</span>
          <div className="h-1 flex-1 bg-zinc-800 rounded-full group cursor-pointer relative"
              onClick={(e) => {
                  const rect = e.currentTarget.getBoundingClientRect();
                  const clickX = e.clientX - rect.left;
                  const percent = clickX / rect.width;
                  const newTime = percent *duration;
                  audioRef.current.currentTime = newTime;
                  setCurrentTime(newTime);
                }}>
            <div className="absolute h-full bg-white rounded-full w-1/3 group-hover:bg-violet-400" 
            style={{ width: `${progressPercent}%` }} />
          </div>

          
          <span className="text-[10px] text-zinc-400">{formatTime(duration)}</span>
        </div>
      </div>

      <div className="flex items-center justify-end gap-3 w-1/3">
        <Volume2 size={20} className="text-zinc-400" />
        <div className="w-24 h-1 bg-zinc-800 rounded-full relative">
          <div 
            className="h-full bg-zinc-400 rounded-full"
            style={{ width: `${volume}%` }} />

            <input
              type="range"
              min="0"
              max="100"
              value={volume}
              onChange={handleVolumeChange}
              className="absolute inset-0 opacity-0 cursor-pointer"
            />
        </div>   
      </div>
        <audio
          ref={audioRef}
          src={songs[currentIndex]?.songUrl || songs[currentIndex]?.audioUrl || songUrl}
          onLoadedMetadata={(e) => setDuration(e.currentTarget.duration)}
          autoPlay={isPlaying}
          loop={isRepeat}
          onTimeUpdate={() => {
            setCurrentTime(audioRef.current.currentTime);
          }}

          onEnded={() => {
            if (!isRepeat) nextSong();
          }} />
    </div>
  );
};
export default Player;