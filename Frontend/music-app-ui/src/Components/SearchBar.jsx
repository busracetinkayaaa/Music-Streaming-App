import { Search } from "lucide-react";

const SearchBar=({value,onChange})=>{
    return (
    <div className="relative w-full max-w-md group">
      <Search className="absolute left-3 top-1/2 -translate-y-1/2 text-zinc-500" size={20} />
      <input
        type="text"
        placeholder="What do you want to play? "
        className="w-full bg-zinc-900 text-white pl-10 pr-4 py-2 rounded-full border border-transparent focus:border-violet-400 outline-none transition-all"
        value={value}
        onChange={onChange}
      />
    </div>
    );
};
export default SearchBar;