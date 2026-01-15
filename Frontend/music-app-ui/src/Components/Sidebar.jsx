import { Home, Disc3, Library } from 'lucide-react';

const Sidebar = ({ setPage }) => {
  const menu = [
    { name: 'Home', id: 'home', icon: <Home size={22} /> },
    { name: 'Explore Albums', id: 'albums', icon: <Disc3 size={22} /> },
    { name: 'Your Library', id: 'playlists', icon: <Library size={22} /> },
  ];

  return (
    <aside className="w-64 bg-black p-4 flex flex-col gap-2">
      <div className="mb-6 px-2">
        <h1 className="text-violet-600 font-extrabold text-2xl tracking-tighter">MUSICIFY</h1>
      </div>
      {menu.map((item) => (
        <button
          key={item.id}
          onClick={() => setPage(item.id)}
          className="flex items-center gap-4 px-3 py-3 text-zinc-400 hover:text-white font-bold transition-all"
        >
          {item.icon}
          <span>{item.name}</span>
        </button>
      ))}
    </aside>
  );
};
export default Sidebar; 