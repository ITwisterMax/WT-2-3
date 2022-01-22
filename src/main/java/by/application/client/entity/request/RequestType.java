package by.application.client.entity.request;

import java.io.Serializable;

/**
 * Enabled request type
 */
public enum RequestType implements Serializable
{
    LOGIN,
    REGISTER,
    GET_ALL,
    GET,
    EDIT,
    CREATE,
}
