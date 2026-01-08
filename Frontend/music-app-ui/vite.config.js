import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import tailwindcss from '@tailwindcss/vite'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react(),
    tailwindcss(),
  ],
  css: {
    overlay: false, // CSS tarafındaki eval kullanımını durdurabilir
  },
  build: {
    sourcemap: false // Build alırken eval kullanımını engeller
  }
})
