function render(header, body) {
    return `
      <!DOCTYPE html>
      <html lang="en">
        <head>
          <meta charset="UTF-8">
          <title>Filmliste</title>
          <link rel="stylesheet" href="/style.css">
        </head>
        <body>
          <h1>Filmliste</h1>
          <p>
            ${header}
          </p>
          <p>
            ${body}
          </p>
        </body>
      </html>
    `;
}

module.exports = { render }
  