# CityFeedback – DDD-Konzept

Die Domäne des CityFeedback-Systems lässt sich in zwei **Bounded Contexts** unterteilen:

---

## 1. Usermanagement

**Zuständig für:**
- Registrierung und Verwaltung von Bürgern und Mitarbeitern
- Authentifizierung (Login)
- Rollen- und Berechtigungsverwaltung

**Aggregate Root:** User
- **User-ID**
- **Name**
- **E-Mail** *(Value Object: EmailAddress)*
- **Passwort/Hash** *(Value Object: PasswordHash)*
- **Rolle** (Bürger/Mitarbeiter/Admin) *(Value Object: UserRole)*
- **Status** (aktiv/deaktiviert) *(Value Object: UserStatus)*
- **Letzte Anmeldung**

> Keine direkte Abhängigkeit zu anderen Contexts.  
> Schnittstelle: Liefert Benutzeridentitäten und Rollen über eine **API/Domain Events** (z. B. `UserRegistered`, `UserAuthenticated`).

---

## 2. Feedbackmanagement

**Verantwortlich für:**
- Erstellung und Verwaltung von Feedback
- Veröffentlichung und Rücknahme von Feedback
- Verwaltung von Kommentaren und Kategorien

**Aggregate Root:** Feedback
- **Feedback-ID**
- **Titel**
- **Beschreibung** *(Value Object: FeedbackText)*
- **Kategorie** *(Value Object: FeedbackCategory)*
- **Status** (erstellt/veröffentlicht/zurückgenommen) *(Value Object: FeedbackStatus)*
- **Benutzer-ID** (Referenz, keine direkte User-Entität)

> Kommunikation mit Usermanagement über **Domain Events** wie `FeedbackCreated`, `FeedbackPublished`, `FeedbackWithdrawn`.  
> Dadurch bleibt Feedbackmanagement unabhängig und nur über IDs gekoppelt.

---

## Domainservices

- **Userregistrierungsdienst**  
  erstellt neue Bürger-/Mitarbeiterkonten  
  → erzeugt Domain Event `UserRegistered`

- **Authentifizierungsdienst**  
  prüft Login-Daten  
  → erzeugt Domain Event `UserAuthenticated`

- **Feedback-Erstellungsdienst**  
  legt neues Feedback an  
  optional erste Validierungen  
  → erzeugt Domain Event `FeedbackCreated`

- **Feedback-Veröffentlichungsservice**  
  setzt Status „veröffentlicht“  
  prüft Sichtbarkeit  
  → erzeugt Domain Event `FeedbackPublished`

- **Feedback-Rücknahmeservice**  
  setzt Status „zurückgenommen“  
  → erzeugt Domain Event `FeedbackWithdrawn`

- **Kommentarservice (optional)**  
  fügt Kommentar zu Feedback hinzu  
  → erzeugt Domain Event `CommentAdded`

---

## Repositories

### BenutzerRepository
- **findeBenutzerNachId(String id)**
- **speichereBenutzer(Benutzer benutzer)**

### FeedbackRepository
- **findeFeedbackNachId(String id)**
- **speichereFeedback(Feedback feedback)**
- *(optional)* **findeFeedbackNachBenutzer(String benutzerId)**

### KommentarRepository *(nur falls Kommentare benötigt sind)*
- **findeKommentareZuFeedback(String feedbackId)**
- **speichereKommentar(Kommentar kommentar)**

---

## Grafiken

### Domänenmodell
<img width="838" height="465" alt="cityfeedback-3-domänenmodell" src="https://github.com/user-attachments/assets/02196330-8fa3-49ed-bf77-9a5814075775" />

### Bounded Contexts
<img width="555" height="703" alt="cityfeedback-3-BoundedContext" src="https://github.com/user-attachments/assets/d3f88956-37e0-4d27-a3a7-c1dfb8d80423" />

---

# Verbesserungen integriert
1. **Klare Schnittstellen**: Usermanagement liefert Identitäten über API/Domain Events, Feedbackmanagement bleibt entkoppelt.
2. **Value Objects**: Fachlich relevante Attribute (E-Mail, Passwort, Status, Kategorie) als Value Objects modelliert.
3. **Domain Events**: Wichtige Aktionen (Registrierung, Authentifizierung, Feedback erstellt/veröffentlicht/zurückgenommen, Kommentar hinzugefügt) als Events definiert.  