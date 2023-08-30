import tkinter as tk

# Constants
WIDTH = 800
HEIGHT = 400
PADDLE_WIDTH = 10
PADDLE_HEIGHT = 60
BALL_RADIUS = 10
PADDLE_SPEED = 4
BALL_SPEED_X = 2
BALL_SPEED_Y = 2

# Game variables
score_a = 0
score_b = 0

# Initialize the window
window = tk.Tk()
window.title("Ping Pong Game")
window.geometry(f"{WIDTH}x{HEIGHT}")
window.resizable(0, 0)

# Create the canvas
canvas = tk.Canvas(window, width=WIDTH, height=HEIGHT, bg="black")
canvas.pack()

# Create the paddles
paddle_a = canvas.create_rectangle(
    50,
    HEIGHT // 2 - PADDLE_HEIGHT // 2,
    50 + PADDLE_WIDTH,
    HEIGHT // 2 + PADDLE_HEIGHT // 2,
    fill="red"
)

paddle_b = canvas.create_rectangle(
    WIDTH - 50 - PADDLE_WIDTH,
    HEIGHT // 2 - PADDLE_HEIGHT // 2,
    WIDTH - 50,
    HEIGHT // 2 + PADDLE_HEIGHT // 2,
    fill="green"
)

# Create the ball
ball = canvas.create_oval(
    WIDTH // 2 - BALL_RADIUS,
    HEIGHT // 2 - BALL_RADIUS,
    WIDTH // 2 + BALL_RADIUS,
    HEIGHT // 2 + BALL_RADIUS,
    fill="white"
)


# Function to move the paddles
def move_paddle_a(event):
    key = event.keysym
    x1, y1, x2, y2 = canvas.coords(paddle_a)

    if key == "w" and y1 > 0:
        canvas.move(paddle_a, 0, -PADDLE_SPEED)
    elif key == "s" and y2 < HEIGHT:
        canvas.move(paddle_a, 0, PADDLE_SPEED)


def move_paddle_b(event):
    key = event.keysym
    x1, y1, x2, y2 = canvas.coords(paddle_b)

    if key == "Up" and y1 > 0:
        canvas.move(paddle_b, 0, -PADDLE_SPEED)
    elif key == "Down" and y2 < HEIGHT:
        canvas.move(paddle_b, 0, PADDLE_SPEED)


# Bind the paddle movement functions to the keyboard events
canvas.bind_all("<KeyPress-w>", move_paddle_a)
canvas.bind_all("<KeyPress-s>", move_paddle_a)
canvas.bind_all("<KeyPress-Up>", move_paddle_b)
canvas.bind_all("<KeyPress-Down>", move_paddle_b)


# Function to update the game
def update_game():
    global score_a, score_b
    global BALL_SPEED_X, BALL_SPEED_Y

    # Move the ball
    canvas.move(ball, BALL_SPEED_X, BALL_SPEED_Y)

    # Get the current ball coordinates
    x1, y1, x2, y2 = canvas.coords(ball)

    # Check for collision with walls
    if y1 <= 0 or y2 >= HEIGHT:
        BALL_SPEED_Y *= -1

    # Check for collision with paddles
    if paddle_collision(paddle_a) or paddle_collision(paddle_b):
        BALL_SPEED_X *= -1

    # Check for scoring
    if x1 <= 0:
        score_b += 1
        canvas.itemconfig(score_text_b, text=score_b)
        reset_ball()
    elif x2 >= WIDTH:
        score_a += 1
        canvas.itemconfig(score_text_a, text=score_a)
        reset_ball()

    # Update the game every 10 milliseconds
    window.after(10, update_game)


# Function to check collision with paddles
def paddle_collision(paddle):
    ball_coords = canvas.coords(ball)
    paddle_coords = canvas.coords(paddle)
    overlapping = canvas.find_overlapping(*paddle_coords)
    return ball in overlapping


# Function to reset the ball position
def reset_ball():
    canvas.coords(ball, WIDTH // 2 - BALL_RADIUS, HEIGHT // 2 - BALL_RADIUS,
                  WIDTH // 2 + BALL_RADIUS, HEIGHT // 2 + BALL_RADIUS)
    global BALL_SPEED_X, BALL_SPEED_Y
    BALL_SPEED_X *= -1
    BALL_SPEED_Y = 2


# Create the score display
score_text_a = canvas.create_text(WIDTH // 2 - 50, 50, text=score_a, fill="white", font=("Arial", 24))
score_text_b = canvas.create_text(WIDTH // 2 + 50, 50, text=score_b, fill="white", font=("Arial", 24))

# Start the game
update_game()

# Run the main loop
window.mainloop()
