import random
import pygame

class Cell:
    def __init__(self, x, y):
        self.x = (y+1) * thickness + (y) * box_size
        self.y = (x+1) * thickness + (x) * box_size
        self.value = 'N'
        self.color = BLACK
        self.textColor = WHITE

    def getValue(self):
        return self.value

    def setValue(self, value):
        self.value = value

    def isMouseIn(self, mousePos):
        if self.x < mousePos[0] < self.x + box_size and self.y < mousePos[1] < self.y + box_size:
            self.color = RED
            return True
        else:
            self.color = BLACK
            return False

    def draw(self, screen):
        # pygame.draw.rect(screen, self.color, [self.x, self.y, box_size, box_size], thickness) # OUTLINE
        if self.value != 'N':
            font = pygame.font.Font(None, 350)
            text = font.render(self.value, 1, self.textColor) # Creating the letter
            text_rect = text.get_rect(center=(self.x + box_size/2, self.y + box_size/2+thickness*1.5)) # Creating the rectangle to center the letter
            screen.blit(text, text_rect) # Drawing the letter

def ValidateMove(move, board):
    if len(move) != 2: # if the move is outside of the expected length
        print("Invalid input: The input is not 2 characters long")
        return False
    if not(move[0] == 'A' or move[0] == 'B' or move[0] == 'C') or not(move[1] == '1' or move[1] == '2' or move[1] == '3'): #if the move is outside of the expected range
        print("Invalid input: The input is out of bounds of the board")
        return False
    moveString = ""
    #move the inputs into a numeric format can we base this one off chr() and ord()?
    if move[0] == 'A':
        moveString = '1'
    if move[0] == 'B':
        moveString = '2'
    if move[0] == 'C':
        moveString = '3'
    moveString += move[1]
    if (board[(int(moveString[1])-1)*3+(int(moveString[0])-1)].value != 'N'):
        print("Invalid input: A token is already placed there")
        return False
    return True

def WarpInput(move): # This function should be modified to not include
    moveString = "" # the several if statements
    moveString = move[1]
    if move[0] == 'A':
        moveString += '1'
    if move[0] == 'B':
        moveString += '2'
    if move[0] == 'C':
        moveString += '3'
    return moveString

def PlayerInput(board):
    #Player logic
    print("Choose a move: Example input: A1 is top left corner")
    playerInput = input("Please enter the coordinates of your move: ")
    while(not(ValidateMove(playerInput, board))):
        print("Invalid move: Example input: A1 is top left corner")
        playerInput = input("Please enter the coordinates of your move: ")
    move = WarpInput(playerInput)
    return [move[0], move[1]]

def printBoard(board):
    for cell in board:
        print(cell.getValue(), end = " ")
    print("")
    for i in range(3):
        print(f"{i+1}|", end = " ")
        print(board[i*3].getValue(), board[i*3+1].getValue(), board[i*3+2].getValue())
    print(" | A B C")
    print("=========")

def WinCondition(board, turn, mute):
    win = 0 # checking all conditions for a win
    if board[0].value == turn and board[1].value == turn and board[2].value == turn:
        win = 2
    if board[3].value == turn and board[4].value == turn and board[5].value == turn:
        win = 3
    if board[6].value == turn and board[7].value == turn and board[8].value == turn:
        win = 4
    if board[0].value == turn and board[3].value == turn and board[6].value == turn:
        win = 5
    if board[1].value == turn and board[4].value == turn and board[7].value == turn:
        win = 6
    if board[2].value == turn and board[5].value == turn and board[8].value == turn:
        win = 7
    if board[0].value == turn and board[4].value == turn and board[8].value == turn:
        win = 8
    if board[2].value == turn and board[4].value == turn and board[6].value == turn:
        win = 9
    if not any(cell.value == 'N' for cell in board):
        if not(mute): # if there is no squares its a draw
            print("Draw")
        return 10
    if win:
        if not(mute):
            print(f"{turn} wins")
        return win
    return 0

def WinnerAnnouce(board): #Announces the winner
    return "D" if not(any(cell.value == 'N' for cell in board)) else "X" if WinCondition(board, 'X', True) else "O" if WinCondition(board, 'O', True) else "D"

def tictactoe():
    board = [Cell(i, j) for i in range(3) for j in range(3)]
    done=False
    while(not(done)):
        printBoard(board)
        [player1x, player1y] = PlayerInput(board)
        board[(int(player1x)-1)*3+(int(player1y)-1)].value = 'X'
        done = WinCondition(board, 'X')
        if not(done):
            printBoard(board)
            [player2x, player2y] = PlayerInput(board)
            board[(int(player2x)-1)*3+(int(player2y)-1)].value = 'O'
            done=WinCondition(board, 'O')

class Easy():
    def __init__(self, turn):
        self.name = "Easy"
        self.turn = turn
        self.opponent = 'O' if turn == 'X' else 'X'
        self.description = "Random moves, please don't lose to this"

    def move(self, board):
        emptyCells = [cell for cell in board if cell.value == 'N']
        randomCell = random.choice(emptyCells)
        randomCell.value = self.turn
        return board

class Medium():
    def __init__(self, turn):
        self.name = "Medium"
        self.turn = turn
        self.opponent = 'O' if turn == 'X' else 'X'
        self.description = "I'm quite difficult, you'll have to be quite smart to get one over on me"
    def move(self, board):
        emptyCells = [cell for cell in board if cell.value == 'N']
        for cell in emptyCells:
            cell.value = 'O'
            if WinCondition(board, self.turn,True):
                return board
            cell.value = 'N'
        for cell in emptyCells:
            cell.value = 'X'
            if WinCondition(board, self.opponent, True):
                cell.value = self.turn
                return board
            cell.value = 'N'
        randomCell = random.choice(emptyCells)
        randomCell.value = self.turn
        return board

class Hard():
    def __init__(self, turn):
        self.name = "Hard"
        self.turn = turn
        self.opponent = 'O' if turn == 'X' else 'X'
        self.description = "Don't even try to beat me, you won't win"
    def isCorner(self, board):
        return (board[0].value == 'N' or board[2].value == 'N' or board[6].value == 'N' or board[8].value == 'N')
    def isEdge(self, board):
        return (board[1].value == 'N' or board[3].value == 'N' or board[5].value == 'N' or board [7].value == 'N')
    def move(self, board):
        if self.isCorner(board) and board[4].value == 'N':
            board[4].value = self.turn
            return board

        emptyCells = [cell for cell in board if cell.value == 'N']
        for cell in emptyCells:
            cell.value = self.turn
            if WinCondition(board, self.turn, True):
                return board
            cell.value = 'N'
        for cell in emptyCells:
            cell.value = self.opponent
            if WinCondition(board, self.opponent, True):
                cell.value = self.turn
                return board
            cell.value = 'N'
        randomCell = random.choice(emptyCells)
        randomCell.value = self.turn
        return board

class Player():
    def __init__(self, turn):
        self.name = "Player"
        self.turn = turn
        self.opponent = 'O' if turn == 'X' else 'X'
        self.description = "Default Description"
    def isCorner(self, board):
        return (board[0].value == 'N' or board[2].value == 'N' or board[6].value == 'N' or board[8].value == 'N')
    def isEdge(self, board):
        return (board[1].value == 'N' or board[3].value == 'N' or board[5].value == 'N' or board [7].value == 'N')
    # def move():
    #     pass
    def move(self, board):
        while True: # wait for the player to select a square
            event = pygame.event.wait()
            if event.type == pygame.MOUSEBUTTONDOWN and event.button == 1: # Left mouse button is described by 1
                mouse_pos = pygame.mouse.get_pos()
                for cell in board:
                    if cell.isMouseIn(mouse_pos) and cell.value == 'N':
                        cell.value = self.turn
                        return # if we have found a successful move, return

def tictactoesingle():
    board = [Cell(i, j) for i in range(3) for j in range(3)]
    done=False
    computer = Easy('O')
    # computer = Medium('O')
    # computer = Hard('O')
    while(not(done)):
        printBoard(board)
        [player1x, player1y] = PlayerInput(board)
        board[(int(player1x)-1)*3+(int(player1y)-1)].value = 'X'
        done = WinCondition(board, 'X', False)
        if not(done):
            board = computer.move(board)
            done=WinCondition(board, 'O', False)

def tictacbot(mute):
    board = [Cell(i, j) for i in range(3) for j in range(3)]
    done=False
    computer2 = Hard('O')
    computer1 = Easy('X')
    while(not(done)):
        board = computer1.move(board)
        done = WinCondition(board, 'X', mute)
        if done:
            break
        if not(done):
            board = computer2.move(board)
            done=WinCondition(board, 'O', mute)
        if done:
            break
    winner = WinnerAnnouce(board)

    return winner

def drawscreen(screen, board):
    #Edges
    pygame.draw.rect(screen, WHITE, [0, 0, screen_size, thickness]) #Top Border
    pygame.draw.rect(screen, WHITE, [0, screen_size-thickness, screen_size, thickness]) #Bottom Border
    pygame.draw.rect(screen, WHITE, [0, 0, thickness, screen_size]) #Left Border
    pygame.draw.rect(screen, WHITE, [screen_size-thickness, 0, thickness, screen_size]) #Right Border
    
    # Board
    pygame.draw.rect(screen, WHITE, [0, box_size+thickness, screen_size, thickness]) #Horizontal Top line
    pygame.draw.rect(screen, WHITE, [0, box_size*2+thickness*2, screen_size, thickness]) #Horizontal Bottom Line
    pygame.draw.rect(screen, WHITE, [box_size+thickness, 0, thickness, screen_size]) #Vertical Left Line
    pygame.draw.rect(screen, WHITE, [box_size*2 + thickness*2, 0, thickness, screen_size]) #Veritcal Right Line

    for cell in board: # Draw the tiles
        cell.draw(screen)

    pygame.display.flip()
    screen.fill(BLACK)

def TicTacAnimate():
    pygame.init() # Creating pygame instance
    board = [Cell(i, j) for i in range(3) for j in range(3)] # Board creation
    screen = pygame.display.set_mode((screen_size,screen_size))
    done=False

    #select Computer
    # player1 = Easy('X')
    # player1 = Medium('X')
    player1 = Hard('X')
    # player1 = Player('X')

    #player 2
    # player2 = Easy('O')
    # player2 = Medium('O')
    player2 = Hard('O')
    # player2 = Player('O')
    while not done:
        for event in pygame.event.get():
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_ESCAPE:
                    done=True

        drawscreen(screen, board)
        print("Player's Turn")
        player1.move(board)
        done = WinCondition(board, 'X', False)
        if not(done):
            drawscreen(screen, board)
            print("Computer's Turn")
            board = player2.move(board)
            printBoard(board)
            done=WinCondition(board, 'O', False)

    #done now so show the winner
    win_color = GREEN
    if (done == 2):
        board[0].textColor = win_color
        board[1].textColor = win_color
        board[2].textColor = win_color
    if (done == 3):
        board[3].textColor = win_color
        board[4].textColor = win_color
        board[5].textColor = win_color
    if (done == 4):
        board[6].textColor = win_color
        board[7].textColor = win_color
        board[8].textColor = win_color
    if (done == 5):
        board[0].textColor = win_color
        board[3].textColor = win_color
        board[6].textColor = win_color
    if (done == 6):
        board[1].textColor = win_color
        board[4].textColor = win_color
        board[7].textColor = win_color
    if (done == 7):
        board[2].textColor = win_color
        board[5].textColor = win_color
        board[8].textColor = win_color
    if (done == 8):
        board[0].textColor = win_color
        board[4].textColor = win_color
        board[8].textColor = win_color
    if (done == 9):
        board[2].textColor = win_color
        board[4].textColor = win_color
        board[6].textColor = win_color
    if (done == 10):
        for cell in board:
            cell.textColor = YELLOW

    for cell in board:
        cell.draw(screen)
    #Final Update
    printBoard(board)
    drawscreen(screen, board)

    while True:
        event = pygame.event.wait()
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_ESCAPE:
                return
            if event.key == pygame.K_r:
                for cell in board:
                    cell.value = 'N'
                    cell.textColor = WHITE
                return TicTacAnimate()

#define variables and constants
box_size = 200
screen_size = 640
thickness = 10

#color
WHITE = (255, 255, 255)
RED = (255, 0, 0)
BLACK = (0, 0, 0)
YELLOW = (255, 255, 0)
BLUE = (0, 0, 255)
GREEN = (0, 255, 0)


#main loop()
TicTacAnimate()
# tictactoe()
# tictactoesingle()
# tictacbot(False)
