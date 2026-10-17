"""Fetch public-domain passages and write core-data/src/main/assets/passages.json.

Run from the repo root:  python tools/fetch_passages.py
"""
import json
import time
import urllib.parse
import urllib.request
from pathlib import Path

TRANSLATION = "web"  # World English Bible (public domain). "kjv" also works.
OUTPUT = Path(__file__).resolve().parent.parent / "core-data/src/main/assets/passages.json"

# (id, reference, recall question, acceptable answers)
PASSAGES = [
    ("john-15-1-8", "John 15:1-8",
     "What does Jesus call himself in this passage?", ["vine"]),
    ("psalm-23", "Psalm 23",
     "What does the psalmist say the Lord is?", ["shepherd"]),
    ("john-3-16-21", "John 3:16-21",
     "Whom did God so love, according to this passage?", ["world"]),
    ("matthew-6-25-34", "Matthew 6:25-34",
     "Which flowers does Jesus tell us to consider?", ["lilies", "lily"]),
    ("philippians-4-4-9", "Philippians 4:4-9",
     "What does Paul say to bring our requests to God with, instead of being anxious?",
     ["prayer", "pray", "petition", "thanksgiving"]),
    ("romans-8-31-39", "Romans 8:31-39",
     "What can nothing separate us from?", ["love"]),
    ("isaiah-40-28-31", "Isaiah 40:28-31",
     "Which bird is mentioned in the promise to those who wait on the Lord?",
     ["eagle", "eagles"]),
    ("1-corinthians-13-4-13", "1 Corinthians 13:4-13",
     "Of faith, hope and love, which is the greatest?", ["love"]),
    ("psalm-121", "Psalm 121",
     "Where does the psalmist lift up his eyes?", ["hills", "hill"]),
    ("luke-15-11-24", "Luke 15:11-24",
     "What animals was the younger son sent to feed?", ["pigs", "pig", "swine"]),
]


def fetch(reference: str) -> str:
    url = f"https://bible-api.com/{urllib.parse.quote(reference)}?translation={TRANSLATION}"
    request = urllib.request.Request(url, headers={"User-Agent": "BibleLock-build-script"})
    with urllib.request.urlopen(request, timeout=30) as response:
        data = json.loads(response.read().decode("utf-8"))
    text = data.get("text") or " ".join(v["text"] for v in data["verses"])
    return " ".join(text.split())  # collapse newlines and repeated spaces


def main() -> None:
    result = []
    for passage_id, reference, question, answers in PASSAGES:
        print(f"Fetching {reference} ...")
        text = fetch(reference)
        if not any(a.lower() in text.lower() for a in answers):
            raise SystemExit(f"None of the answers {answers} appear in {reference}. Fix the question.")
        result.append({
            "id": passage_id,
            "reference": reference,
            "text": text,
            "recallPrompt": question,
            "acceptableAnswers": answers,
        })
        time.sleep(3)  # stay well under the 15 requests / 30 seconds limit

    OUTPUT.parent.mkdir(parents=True, exist_ok=True)
    OUTPUT.write_text(json.dumps(result, indent=2, ensure_ascii=False), encoding="utf-8")
    print(f"Wrote {len(result)} passages to {OUTPUT}")


if __name__ == "__main__":
    main()