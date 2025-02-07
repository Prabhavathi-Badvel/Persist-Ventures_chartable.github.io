import React, { useState } from 'react';
import { LineChart, XAxis, YAxis, Tooltip, Area, ComposedChart } from 'recharts';
import { Play, Users, Clock, Star, Menu, Bell, Search, Download, ChevronDown, Radio } from 'lucide-react';

const data = Array(30).fill().map((_, i) => ({
  day: `Day ${i + 1}`,
  plays: Math.floor(Math.random() * 3000 + 1000),
  listeners: Math.floor(Math.random() * 2000 + 500),
}));

const episodes = [
  { name: 'The Future of AI in 2025', plays: 12453, duration: '45:32', rating: 4.8, trend: '+15.4%' },
  { name: 'Web Development Best Practices', plays: 10234, duration: '38:15', rating: 4.9, trend: '+12.7%' },
  { name: 'Startup Success Stories', plays: 9876, duration: '42:18', rating: 4.7, trend: '+8.3%' },
  { name: 'Mobile App Development Trends', plays: 8765, duration: '36:45', rating: 4.6, trend: '+5.9%' },
];

const CustomTooltip = ({ active, payload }) => {
  if (active && payload && payload.length) {
    return (
      <div className="bg-gray-900 p-4 rounded-lg shadow-xl border border-gray-800">
        <p className="text-gray-400 mb-2">{payload[0].payload.day}</p>
        <p className="text-purple-500 mb-1">
          Plays: {payload[0].value.toLocaleString()}
        </p>
        <p className="text-pink-500">
          Listeners: {payload[1].value.toLocaleString()}
        </p>
      </div>
    );
  }
  return null;
};

export default function Dashboard() {
  const [hoveredCard, setHoveredCard] = useState(null);

  const StatCard = ({ icon: Icon, title, value, trend, index }) => (
    <div
      className={`bg-gray-900 rounded-xl p-6 transition-all duration-300 hover:shadow-2xl hover:shadow-purple-500/10 border border-gray-800 hover:border-purple-500/20 ${
        hoveredCard === index ? 'transform scale-105' : ''
      }`}
      onMouseEnter={() => setHoveredCard(index)}
      onMouseLeave={() => setHoveredCard(null)}
    >
      <div className="flex items-center gap-2 text-green-400 text-sm mb-3">
        <div className="bg-green-400/10 p-2 rounded-lg">
          <Icon size={16} />
        </div>
        <span>{trend}</span>
      </div>
      <div className="text-4xl font-bold mb-2">{value}</div>
      <div className="text-gray-400 text-sm">{title}</div>
    </div>
  );

  return (
    <div className="min-h-screen bg-gray-950 text-white p-8">
      {/* Header */}
      <nav className="flex items-center justify-between mb-12">
        <div className="flex items-center gap-8">
          <div className="flex items-center gap-2 text-xl font-bold">
            <Radio className="text-purple-500" size={24} />
            <span>DecibelX</span>
          </div>
          <div className="flex items-center gap-6 text-gray-400">
            <button className="hover:text-white transition-colors">Home</button>
            <button className="hover:text-white transition-colors">Podcasts</button>
            <button className="hover:text-white transition-colors">Analytics</button>
            <button className="bg-purple-500/10 text-purple-500 px-4 py-2 rounded-lg">Dashboard</button>
          </div>
        </div>
        <div className="flex items-center gap-6">
          <button className="text-gray-400 hover:text-white transition-colors">
            <Bell size={20} />
          </button>
          <button className="bg-purple-600 hover:bg-purple-700 transition-colors px-6 py-2 rounded-lg">
            Sign Up
          </button>
        </div>
      </nav>

      {/* Dashboard Header */}
      <div className="flex justify-between items-center mb-12">
        <div>
          <h1 className="text-3xl font-bold mb-3">Creator Dashboard</h1>
          <p className="text-gray-400">Track and analyze your podcast performance</p>
        </div>
        <div className="flex gap-4">
          <button className="flex items-center gap-2 bg-gray-900 hover:bg-gray-800 transition-colors px-4 py-2 rounded-lg border border-gray-800">
            Last 30 Days <ChevronDown size={16} />
          </button>
          <button className="flex items-center gap-2 bg-purple-600 hover:bg-purple-700 transition-colors px-4 py-2 rounded-lg">
            <Download size={16} /> Download Report
          </button>
        </div>
      </div>

      {/* Stats Grid */}
      <div className="grid grid-cols-4 gap-6 mb-12">
        <StatCard icon={Play} title="Total Plays" value="234.5K" trend="+12.3%" index={0} />
        <StatCard icon={Users} title="Unique Listeners" value="45.2K" trend="+8.7%" index={1} />
        <StatCard icon={Clock} title="Avg. Listen Time" value="32:45" trend="+15.4%" index={2} />
        <StatCard icon={Star} title="Avg. Rating" value="4.8" trend="+0.2" index={3} />
      </div>

      {/* Chart */}
      <div className="bg-gray-900 rounded-xl p-8 mb-12 border border-gray-800">
        <div className="flex justify-between items-center mb-8">
          <div>
            <h2 className="text-xl font-bold mb-2">Performance Overview</h2>
            <p className="text-gray-400">Daily plays and unique listeners</p>
          </div>
          <div className="flex gap-6">
            <div className="flex items-center gap-2">
              <div className="w-3 h-3 rounded-full bg-purple-500"></div>
              <span className="text-gray-400">Plays</span>
            </div>
            <div className="flex items-center gap-2">
              <div className="w-3 h-3 rounded-full bg-pink-500"></div>
              <span className="text-gray-400">Listeners</span>
            </div>
          </div>
        </div>
        <ComposedChart width={1000} height={400} data={data}>
          <defs>
            <linearGradient id="colorPlays" x1="0" y1="0" x2="0" y2="1">
              <stop offset="5%" stopColor="#8B5CF6" stopOpacity={0.3}/>
              <stop offset="95%" stopColor="#8B5CF6" stopOpacity={0}/>
            </linearGradient>
            <linearGradient id="colorListeners" x1="0" y1="0" x2="0" y2="1">
              <stop offset="5%" stopColor="#EC4899" stopOpacity={0.3}/>
              <stop offset="95%" stopColor="#EC4899" stopOpacity={0}/>
            </linearGradient>
          </defs>
          <XAxis dataKey="day" stroke="#4B5563" />
          <YAxis stroke="#4B5563" />
          <Tooltip content={<CustomTooltip />} />
          <Area 
            type="monotone" 
            dataKey="plays" 
            stroke="#8B5CF6" 
            strokeWidth={2}
            fillOpacity={1} 
            fill="url(#colorPlays)" 
          />
          <Area 
            type="monotone" 
            dataKey="listeners" 
            stroke="#EC4899" 
            strokeWidth={2}
            fillOpacity={1} 
            fill="url(#colorListeners)" 
          />
        </ComposedChart>
      </div>

      {/* Episodes Table */}
      <div className="bg-gray-900 rounded-xl p-8 border border-gray-800">
        <div className="flex justify-between items-center mb-8">
          <div>
            <h2 className="text-xl font-bold mb-2">Top Performing Episodes</h2>
            <p className="text-gray-400">Your most popular content</p>
          </div>
          <button className="text-purple-500 hover:text-purple-400 transition-colors">
            View All Episodes
          </button>
        </div>
        <table className="w-full">
          <thead>
            <tr className="text-gray-400 border-b border-gray-800">
              <th className="text-left pb-4 font-medium">Episode</th>
              <th className="text-right pb-4 font-medium">Plays</th>
              <th className="text-right pb-4 font-medium">Duration</th>
              <th className="text-right pb-4 font-medium">Rating</th>
              <th className="text-right pb-4 font-medium">Trend</th>
            </tr>
          </thead>
          <tbody>
            {episodes.map((episode, i) => (
              <tr key={i} className="border-b border-gray-800 hover:bg-gray-800/50 transition-colors">
                <td className="py-4 flex items-center gap-3">
                  <div className="bg-purple-500/10 p-2 rounded-lg">
                    <Play size={16} className="text-purple-500" />
                  </div>
                  {episode.name}
                </td>
                <td className="text-right py-4">{episode.plays.toLocaleString()}</td>
                <td className="text-right py-4">{episode.duration}</td>
                <td className="text-right py-4 flex items-center justify-end gap-1">
                  <Star size={16} className="text-yellow-400" />
                  {episode.rating}
                </td>
                <td className="text-right py-4 text-green-400">{episode.trend}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}