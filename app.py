import sqlite3
import re
from flask import Flask, render_template, g, jsonify

app = Flask(__name__)

# Database path
DATABASE = app.root_path + "/db/dictionary.db"

def get_db():
    db = getattr(g, '_database', None)
    if db is None:
        try:
            db = g._database = sqlite3.connect(DATABASE)
            db.row_factory = sqlite3.Row
        except sqlite3.Error as e:
            print("Database connection error:", e)
            return None
    return db

def normalize_query_english(search_word):
    return re.sub(u'[^a-zA-Z\-]', '', search_word.lower())

@app.route("/api/get/<word>")
def get_dict(word):
    word = normalize_query_english(word)
    cursor = get_db().cursor()
    try:
        cursor.execute("SELECT * FROM entries WHERE word = ?", (word,))
        result = cursor.fetchone()
    except sqlite3.Error as e:
        return jsonify({"error": "Database error"})

    if result:
        result_dict = dict(result)
        return jsonify(result_dict)
    else:
        return jsonify({"error": "Word not found"})

@app.route("/api/suggestion/<beginswith>")
def api_get_suggestion(beginswith):
    beginswith = normalize_query_english(beginswith)
    data = get_suggestions(beginswith, limit=10)
    return jsonify(suggestions=data)

def get_suggestions(beginswith, limit):
    cur = get_db().cursor()
    try:
        cur.execute("SELECT * FROM entries WHERE word LIKE ? LIMIT ?", (beginswith + '%', limit))
        result = cur.fetchall()
    except sqlite3.Error as e:
        return []

    data = []
    for r in result:
        data.append(dict(r))
    return data

@app.teardown_appcontext
def close_connection(exp):
    db = getattr(g, '_database', None)
    if db is not None:
        db.close()

if __name__ == "__main__":
    app.run(debug=True)
