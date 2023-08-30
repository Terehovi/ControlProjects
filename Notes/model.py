import json
import os
import datetime

# Функция для создания новой заметки
def create_note():
    title = input("Введите заголовок заметки: ")
    body = input("Введите текст заметки: ")
    timestamp = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    
    note = {
        "id": len(notes) + 1,
        "title": title,
        "body": body,
        "timestamp": timestamp
    }
    
    notes.append(note)
    save_notes()

# Функция для вывода всех заметок
def display_notes():
    for note in notes:
        print(f"ID: {note['id']}")
        print(f"Заголовок: {note['title']}")
        print(f"Заметка: {note['body']}")
        print(f"Дата/Время: {note['timestamp']}")
        print("=" * 30)

# Функция для редактирования заметки
def edit_note():
    note_id = int(input("Введите ID заметки для редактирования: "))
    for note in notes:
        if note['id'] == note_id:
            new_title = input("Введите новый заголовок заметки: ")
            new_body = input("Введите новый текст заметки: ")
            note['title'] = new_title
            note['body'] = new_body
            note['timestamp'] = datetime.datetime.now().strftime("%d-%m-%Y %H:%M:%S")
            save_notes()
            print("Заметка успешно отредактирована.")
            return
    print("Заметка с указанным ID не найдена.")

# Функция для удаления заметки
def delete_note():
    note_id = int(input("Введите ID заметки для удаления: "))
    for note in notes:
        if note['id'] == note_id:
            notes.remove(note)
            save_notes()
            print("Заметка успешно удалена.")
            return
    print("Заметка с указанным ID не найдена.")

# Функция для сохранения заметок в JSON файл
def save_notes():
    with open("notes.json", "w") as file:
        json.dump(notes, file)

# Загрузка заметок из JSON файла (если он существует)
if os.path.exists("notes.json"):
    with open("notes.json", "r") as file:
        notes = json.load(file)
else:
    notes = []

def main_menu() -> int:
    print('''\n Главное меню
             1. Создать новую заметку
             2. Вывести все заметки
             3. Редактировать заметку
             4. Удалить заметку
             5. Выйти\n''')
    while True:    
        choice = input("Выберите действие: ")
        if choice.isdigit() and 0 < int(choice) < 6:
            return int(choice)