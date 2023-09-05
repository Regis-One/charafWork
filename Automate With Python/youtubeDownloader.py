from pytube import YouTube
import pytube.cli as pytube_cli
import tkinter as tk 
from tkinter import ttk, messagebox



def download_video():

        def progress_function(stream, chunk, bytes_remaining):
                progress = round((1-bytes_remaining/youtubeObject_video.filesize)*100, 2)
                if (progress < 100.0):
                    progress_message = progress, '% done...'
                    download_progress.delete(0, tk.END)
                    download_progress.insert(tk.END, progress_message)

               
                
                app.update() 
               

        

        link = link_entry.get()
        title = title_entry.get()

        try:
                youtubeObject = YouTube(link, on_progress_callback=progress_function)
                output_path = "C:\\Users\\16132\\Downloads"
                youtubeObject_video = youtubeObject.streams.get_highest_resolution()
                print(f"Downloading '{title}'...")

                # Using the pytube_cli to download the video with progress bar
                youtubeObject_video.download(filename=title + ".mp4", output_path= output_path)

               

        except Exception as e:
                print("An error has occurred:", e)


        success_message = f"Download of '{title}' is completed successfully"
        download_progress.insert(tk.END, success_message)

      
        






app = tk.Tk()
app.title("YouTube downloader")
canvas = ttk.Frame(app, padding="20")
canvas.grid(row = 0, column = 0)
link_label = ttk.Label(canvas, text ="Link: ")
link_label.grid(row = 0, column = 0)
link_entry = ttk.Entry(canvas, width = 20)
link_entry.grid(row=0, column = 1)

title_label = ttk.Label(canvas, text ="Title: ")
title_label.grid(row = 1, column = 0, pady=5)
title_entry = ttk.Entry(canvas, width = 20)
title_entry.grid(row=1, column = 1)

download_button = ttk.Button(canvas, text = "Download video", command= download_video)
download_button.grid(row = 2, column=0)

download_progress = tk.Listbox(canvas, height=11, width=30)
download_progress.grid(row=3)

  

app.mainloop()

