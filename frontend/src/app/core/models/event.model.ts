export interface Event {
    id?: number;
    title: string;
    description: string;
    eventDate: string;
    location: string;
    deleted?: boolean;
    createdAt?: string;
    updatedAt?: string;
}
