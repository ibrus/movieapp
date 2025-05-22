export interface Movie {
    id:          number;
    title:       string;
    poster_path: string;
    // add more TMDb fields if needed
}

export interface MoviePage {
    page:          number;
    results:       Movie[];
    total_pages:   number;
    total_results: number;
}
