# Eigenfaces — Face Recognition System

**Academic Group Project · 1st Semester (2024/2025) · Computer Engineering — ISEP**

A Java application for representing, reconstructing, and identifying facial images using the **Eigenfaces** method. Developed for *Laboratório/Projeto I (LAPR1)* at the Instituto Superior de Engenharia do Porto (ISEP).

The project applies **linear algebra**, eigenvalue/eigenvector decomposition, image processing, and **Euclidean distance** to compare a facial image against a reference dataset. It provides both an interactive console menu and a command-line mode for running individual operations.

## Features

The application implements four main functionalities:

| Feature | Description |
| --- | --- |
| **1. Symmetric matrix eigendecomposition** | Computes eigenvalues and eigenvectors, reconstructs an approximation using `k` eigenvectors, and calculates the mean absolute error. |
| **2. Facial image reconstruction** | Builds an Eigenfaces representation from facial-image CSV files, reconstructs the images using `k` components, and exports image and CSV results. |
| **3. Face similarity / identification** | Projects a query image and the reference images into the Eigenfaces space, then identifies the closest match using Euclidean distance. |
| **4. Image generation** | Generates new facial-image representations from the dataset's mean face and selected Eigenfaces components. |

For features 2–4, the source data is read from **CSV files containing pixel matrices**. The code also produces `.jpg` outputs for visual inspection.

## Example Results

The repository includes example outputs produced during development:

| Eigenface | Reconstructed image | Closest-match output |
| :---: | :---: | :---: |
| ![Example eigenface](Projeto3/src/outputs/funcionalidade2/Eigenfaces/Eigenface_1.jpg) | ![Example reconstructed image](Projeto3/src/outputs/funcionalidade2/ImagensReconstruidas/imagem_1.jpg) | ![Example closest-match result](Projeto3/src/outputs/funcionalidade3/Identificacao/Imagem_Mais_Parecida_1.jpg) |

These files are already part of the repository; the README does not require additional images to be uploaded.

## Technologies

| Area | Technologies |
| --- | --- |
| Programming language | Java |
| Mathematics | Eigenvalues, eigenvectors, matrix operations, Euclidean distance |
| Numerical library | Apache Commons Math **3.6.1** |
| Image processing | Java AWT, `ImageIO` |
| Data format | CSV matrices, JPG outputs |
| Development environment | IntelliJ IDEA (project configuration included) |
| Testing | Java test runner (`Testes.java`) |

> **Build note:** This repository is an **IntelliJ Java project**, not a Maven or Gradle project. The Apache Commons Math JAR is **not bundled** with the source code. The committed IntelliJ settings refer to a library stored in a developer's local `Downloads` folder, so each new developer must configure that dependency locally.

## Repository Structure

```text
.
├── README.md
└── Projeto3/
    ├── src/
    │   ├── Projeto3.java          # Entry point: interactive / command-line modes
    │   ├── UI.java                # Menu and feature orchestration
    │   ├── calcularMatrizes.java   # Linear algebra and Eigenfaces algorithms
    │   ├── leiturasaidadados.java  # CSV/image input and output
    │   ├── Testes.java            # Manual algorithm test runner
    │   ├── inputs/                # Sample matrices and facial-image datasets
    │   ├── outputs/               # Example CSV/JPG results
    │   └── vetores.csv            # Intermediate image vectors
    └── .idea/                     # IntelliJ project settings
```

## Getting Started

### Prerequisites

- **Java JDK** (the committed IntelliJ project is configured for **JDK 23**).
- **Apache Commons Math 3.6.1** (`commons-math3-3.6.1.jar`).
- **IntelliJ IDEA** (recommended), or a terminal with `javac` and `java`.

Download the Apache Commons Math 3.6.1 JAR from the official Apache Commons Math distribution / Maven Central. A prebuilt application JAR is **not included** in this repository.

### 1. Clone the repository

```bash
git clone https://github.com/1241182AfonsoMachado/eigenfaces-face-recognition-sem1.git
cd eigenfaces-face-recognition-sem1
```

### 2. Run with IntelliJ IDEA (recommended)

1. In IntelliJ IDEA, open the **`Projeto3`** folder as a Java project.
2. Configure a Java SDK under **Project Structure → Project** (the original settings use JDK 23).
3. Under **Project Structure → Libraries**, add the downloaded `commons-math3-3.6.1.jar` to the project or module. Replace the old machine-specific library path if IntelliJ reports a missing dependency.
4. Open `Projeto3/src/Projeto3.java` and run its `main` method.
5. In the run configuration, set the **working directory to `Projeto3`** (the directory containing `src`). This matters because the application uses relative paths such as `src/inputs/...`.
6. With no program arguments, the application opens its interactive console menu. To run a single feature, pass the options described below as **Program arguments**.

### 3. Compile and run from the terminal (Windows / Git Bash)

After cloning the repository, go to the Java project directory:

```bash
cd Projeto3
mkdir -p lib build
```

Place the downloaded **`commons-math3-3.6.1.jar`** in the `lib/` directory, then compile the Java files:

```bash
javac -cp "lib/commons-math3-3.6.1.jar" -d build src/*.java
```

Run the interactive application:

```bash
java -cp "build;lib/commons-math3-3.6.1.jar" Projeto3
```

> On **Windows**, Java separates classpath entries with `;` (including when invoked from Git Bash). On Linux/macOS, replace `;` with `:`. Some output paths in the source code contain Windows-style backslashes, so Windows is the most straightforward environment for running the unmodified project.

## Usage

### Interactive mode

Run `Projeto3` without arguments and select one of the options shown in the console:

```text
1. Symmetric matrix eigendecomposition
2. Facial image reconstruction using Eigenfaces
3. Closest-image identification
4. Facial image generation using Eigenfaces
5. Exit
```

You will be prompted for the relevant input CSV or dataset directory and the number of components (`k`).

### Command-line mode

The supported arguments are implemented in `Projeto3.java`:

| Argument | Meaning | Required for |
| --- | --- | --- |
| `-f` | Feature number (`1` to `4`) | All command-line operations |
| `-k` | Number of eigenvectors / Eigenfaces | Supply when running a feature |
| `-i` | Input CSV file | Features 1 and 3 |
| `-d` | Dataset directory containing image CSV files | Features 2, 3 and 4 |

The examples below are intended for **Windows / Git Bash**, executed from inside `Projeto3/` after compiling the sources. They use the sample data bundled in the repository.

```bash
# Optional shorthand for the classpath
CP="build;lib/commons-math3-3.6.1.jar"

# Feature 1 — matrix eigendecomposition
java -cp "$CP" Projeto3 -f 1 -k 5 -i "src/inputs/Funcionalidade1/exampleInputFunc1.csv"

# Reference facial-image dataset (40 sample CSV matrices)
DATASET="src/inputs/Funcionalidades 2 e 3/exemploMatrizesImagensCSVFuncionalidades2e3/exemploMatrizesImagensCSVFuncionalidades2e3"

# Feature 2 — reconstruct images with 10 Eigenfaces
java -cp "$CP" Projeto3 -f 2 -k 10 -d "$DATASET"

# Feature 3 — identify the closest image to a query
QUERY="src/inputs/Funcionalidade 3/exemploImagensInputFuncionalidade3/exemploImagensInputFuncionalidade3/image_038.csv"
java -cp "$CP" Projeto3 -f 3 -k 10 -i "$QUERY" -d "$DATASET"

# Feature 4 — generate a facial-image representation
java -cp "$CP" Projeto3 -f 4 -k 10 -d "$DATASET"
```

The application stores examples and generated outputs under `Projeto3/src/outputs/funcionalidade1` through `funcionalidade4` (depending on the selected feature). Some operations also rewrite `Projeto3/src/vetores.csv`, an intermediate data file.

## Testing

The repository has a standalone test class, **`Testes.java`**, which exercises matrix operations and Eigenfaces-related calculations. It prints validation messages to the console; these are **not JUnit tests**.

In IntelliJ, run the `main` method of `Testes.java`. Alternatively, after compiling the sources as shown above, run:

```bash
java -cp "build;lib/commons-math3-3.6.1.jar" Testes
```

The checks cover matrix transposition and multiplication, matrix approximation and mean absolute error, eigenvector handling, mean-face calculations, image reconstruction, Euclidean distance and image generation.

## Academic Context and Team

- **Course:** Laboratório/Projeto I (LAPR1)
- **Degree:** BSc in Computer Engineering — Instituto Superior de Engenharia do Porto (ISEP)
- **Period:** 1st semester, academic year 2024/2025
- **Project group:** DIJ_03

The original project was developed collaboratively by:

- Afonso Machado (1241182)
- José Oliveira (1241277)
- Beatriz Pinto (1241260)
- João Marques (1230613)

## Notes and Limitations

- Input facial images are represented as **CSV pixel matrices**, not as arbitrary image files uploaded through a graphical interface.
- Example outputs included in the repository illustrate algorithm behavior; they are **not an accuracy benchmark** for face recognition.
- The repository has no Maven/Gradle build configuration or bundled Commons Math JAR; the numerical dependency must be configured separately.
- Several input/output paths are written for Windows; other operating systems may require adjustments.
