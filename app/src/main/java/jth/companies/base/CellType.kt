package jth.companies.base

enum class CellType(val type: String, val viewType : Int) {
    CELL_TYPE_COMPANY("CELL_TYPE_COMPANY", 0),
    CELL_TYPE_REVIEW("CELL_TYPE_REVIEW", 1),
    CELL_TYPE_HORIZONTAL_THEME("CELL_TYPE_HORIZONTAL_THEME", 2)
}