package com.example.tictactoe

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

private const val EMPTY = "empty"
private const val DRAW = "Draw! try again"

class MainActivity : AppCompatActivity() {

    // This is how I "saved" the board of the game. All the cells initialized to EMPTY.
    private var boardCells = arrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
        EMPTY, EMPTY)

    // This enum class contains: image, status turn and status win, in accordance with the players
    private enum class Turn (val image : Int, val statusTurn : String, val statusWin : String){
        MOLE (R.drawable.mole, "Mole's turn", "Mole wins!"),
        CARROT (R.drawable.carrot, "Carrot's turn", "Carrot wins!")
    }

    // This variable saves the current turn
    private var currentTurn = Turn.CARROT

    // By this variable I will check if the game is ended
    private var isGameEnded = false

    // All the winning options in the board's cell (by rows, by columns, by diagonals)
    private val winningOptions = arrayOf(intArrayOf(0, 1, 2), intArrayOf(3, 4, 5),
        intArrayOf(6, 7, 8), intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8), intArrayOf(2, 4, 6))

    /**
     * The main function of the game
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        defineClickable()
    }

    /**
     * Defines the "setOfClickListener" for all the clickable objects in the game
     */
    private fun defineClickable() {
        val button0 = findViewById<ImageView>(R.id.button0Image)
        button0.setOnClickListener {
            if (!isGameEnded){
                playTurn(button0)
            }
        }
        val button1 = findViewById<ImageView>(R.id.button1Image)
        button1.setOnClickListener {
            if (!isGameEnded){
                playTurn(button1)
            }
        }
        val button2 = findViewById<ImageView>(R.id.button2Image)
        button2.setOnClickListener {
            if (!isGameEnded){
                playTurn(button2)
            }
        }
        val button3 = findViewById<ImageView>(R.id.button3Image)
        button3.setOnClickListener {
            if (!isGameEnded){
                playTurn(button3)
            }
        }
        val button4 = findViewById<ImageView>(R.id.button4Image)
        button4.setOnClickListener {
            if (!isGameEnded){
                playTurn(button4)
            }
        }
        val button5 = findViewById<ImageView>(R.id.button5Image)
        button5.setOnClickListener {
            if (!isGameEnded){
                playTurn(button5)
            }
        }
        val button6 = findViewById<ImageView>(R.id.button6Image)
        button6.setOnClickListener {
            if (!isGameEnded){
                playTurn(button6)
            }
        }
        val button7 = findViewById<ImageView>(R.id.button7Image)
        button7.setOnClickListener {
            if (!isGameEnded){
                playTurn(button7)
            }
        }
        val button8 = findViewById<ImageView>(R.id.button8Image)
        button8.setOnClickListener {
            if (!isGameEnded){
                playTurn(button8)
            }
        }

        val resetButton = findViewById<Button>(R.id.restartButton)
        resetButton.setOnClickListener {
            resetGame()
        }
    }

    /**
     * Responsible for the actions that the players will perform and the state on board-
     * this function checks for winning, draw and switching turns.
     */
    private fun playTurn(button: ImageView){
        val statusText = findViewById<TextView>(R.id.statusGame)
        val tappedImage = button.tag.toString().toInt()
        if (isCellEmpty(tappedImage)){
            button.setImageResource(currentTurn.image) // put the image
            boardCells[tappedImage] = currentTurn.toString() // fill the cell in board
            if (checkIfWinningSequence()){
                statusText.text = currentTurn.statusWin
                isGameEnded = true
                return
            }
            if (checkIfDraw()){
                statusText.text = DRAW
                isGameEnded = true
                return
            }
            else {
                switchTurn(statusText)
            }
        }

    }

    /**
     * Switches the players and update the statusText.
     */
    private fun switchTurn(statusText: TextView) {
        currentTurn = when (currentTurn) {
            Turn.MOLE -> Turn.CARROT
            Turn.CARROT -> Turn.MOLE
        }
        statusText.text = currentTurn.statusTurn
    }

    /**
     * Checks if draw.
     */
    private fun checkIfDraw(): Boolean {
        for (cell in boardCells.indices) {
            if (boardCells[cell] == EMPTY){
                return false
            }
        }
        return true
    }

    /**
     * Checks if empty.
     */
    private fun isCellEmpty(cell : Int) : Boolean {
        return boardCells[cell] == EMPTY
    }

    /**
     * Checks if there is a winning sequence in board.
     */
    private fun checkIfWinningSequence(): Boolean {
        for (sequence in winningOptions){
            if (checkMatchInCells(sequence)) return true
        }
        return false
    }

    /**
     * Search for match in the marks on board's cells.
     */
    private fun checkMatchInCells(sequence: IntArray): Boolean {
        if (boardCells[sequence[0]] == boardCells[sequence[1]] &&
            boardCells[sequence[1]] == boardCells[sequence[2]] &&
            boardCells[sequence[0]] != EMPTY
        ) {
            return true
        }
        return false
    }

    /**
     * Resets the game.
     */
    private fun resetGame() {
        isGameEnded = false
        currentTurn = Turn.CARROT
        resetBoardCells()
        resetBoardButtons()

        val statusText = findViewById<TextView>(R.id.statusGame)
        statusText.text = currentTurn.statusTurn
    }

    /**
     * Resets board's cells.
     */
    private fun resetBoardCells() {
        for (cell in boardCells.indices) {
            boardCells[cell] = EMPTY
        }
    }

    /**
     * Reset board's buttons.
     */
    private fun resetBoardButtons() {
        (findViewById<ImageView>(R.id.button0Image)).setImageResource(0)
        (findViewById<ImageView>(R.id.button1Image)).setImageResource(0)
        (findViewById<ImageView>(R.id.button2Image)).setImageResource(0)
        (findViewById<ImageView>(R.id.button3Image)).setImageResource(0)
        (findViewById<ImageView>(R.id.button4Image)).setImageResource(0)
        (findViewById<ImageView>(R.id.button5Image)).setImageResource(0)
        (findViewById<ImageView>(R.id.button6Image)).setImageResource(0)
        (findViewById<ImageView>(R.id.button7Image)).setImageResource(0)
        (findViewById<ImageView>(R.id.button8Image)).setImageResource(0)
    }

    /**
     * Responsible for saving the current data if there is an interruption.
     */
    override fun onSaveInstanceState(outState : Bundle) {
        outState.putSerializable("CUR_PLAYER", currentTurn)
        outState.putSerializable("CUR_BOARD", boardCells)
        outState.putSerializable("IS_GAME_ENDED", isGameEnded)
        val curText = findViewById<TextView>(R.id.statusGame).text.toString()
        outState.putSerializable("CUR_TEXT", curText)
        super.onSaveInstanceState(outState)
    }

    /**
     * Responsible for restoring the current data if there is an interruption.
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        isGameEnded = savedInstanceState.getSerializable("IS_GAME_ENDED") as Boolean
        currentTurn = savedInstanceState.getSerializable("CUR_PLAYER") as Turn
        boardCells = savedInstanceState.getSerializable("CUR_BOARD") as Array<String>
        val curText = savedInstanceState.getSerializable("CUR_TEXT")

        restoreUI(curText)

        super.onRestoreInstanceState(savedInstanceState)
    }

    /**
     * Restore all the UI objects in the game.
     */
    private fun restoreUI(curText: Serializable?) {
        for (cell in boardCells.indices) {
            if (boardCells[cell] != EMPTY) {
                val button = findViewById<ImageView>(
                    resources.getIdentifier(
                        "button${cell}Image", "id", packageName
                    )
                )
                when (boardCells[cell]) {
                    Turn.MOLE.toString() -> button.setImageResource(R.drawable.mole)
                    Turn.CARROT.toString() -> button.setImageResource(R.drawable.carrot)
                }
            }
        }

        val statusText = findViewById<TextView>(R.id.statusGame)
        statusText.text = curText.toString()
    }
}