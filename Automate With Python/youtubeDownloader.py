from pytube import YouTube
import pytube.cli as pytube_cli

link = input("Please enter the link of the video you wish to download: ")


def progress_function(stream, chunk, bytes_remaining):
        progress = round((1-bytes_remaining/youtubeObject_video.filesize)*100, 2)
        if (progress < 100.0):
            print(progress, '% done...')

        




youtubeObject = YouTube(link, on_progress_callback=progress_function)
youtubeObject_video = youtubeObject.streams.get_highest_resolution()
try:
        print(f"Downloading '{youtubeObject.title}'...")

        # Using the pytube_cli to download the video with progress bar
        youtubeObject_video.download(filename=youtubeObject.title + ".mp4")

        print(f"Download of '{youtubeObject.title}' is completed successfully")

except Exception as e:
        print("An error has occurred:", e)



