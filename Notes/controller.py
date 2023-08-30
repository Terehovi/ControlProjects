import model
def start():
    while True:
        choice = model.main_menu()
        match choice:
            case 1:
                model.create_note()
            case 2:
                model.display_notes()
            case 3:
                model.edit_note()
            case 4:
                model.delete_note()
            case 5:
                break
        