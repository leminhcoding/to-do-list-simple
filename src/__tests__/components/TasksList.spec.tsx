import React from 'react';
import { render, fireEvent } from '@testing-library/react-native';

import { TasksList } from '../../components/TasksList';

let tasks: {
  id: number;
  title: string;
  done: boolean;
}[] = [];

let mockedRemoveTask: jest.Mock;
let mockedToggleTaskDone: jest.Mock;
let mockedUpdateTaskName: jest.Mock;

describe('MyTasksList', () => {

  beforeAll(() => {
    tasks = [
      {
        id: new Date().getTime(),
        title: 'Việc 1',
        done: false
      },
      {
        id: new Date().getTime() + 1,
        title: 'Việc 2',
        done: false
      },
      {
        id: new Date().getTime() + 2,
        title: 'Việc 3',
        done: true
      },
    ];

    mockedRemoveTask = jest.fn();
    mockedToggleTaskDone = jest.fn();
    mockedUpdateTaskName = jest.fn();
  });

  it('should be able to render all tasks', () => {
    const { getByText } = render(<TasksList tasks={tasks} removeTask={mockedRemoveTask} toggleTaskDone={mockedToggleTaskDone} updateTaskName={mockedUpdateTaskName} />)
    
    getByText('Việc 1');
    getByText('Việc 2');
    getByText('Việc 3');
  });

  it('should be able to handle "removeTask" event', () => {
    const { getByTestId } = render(<TasksList tasks={tasks} removeTask={mockedRemoveTask} toggleTaskDone={mockedToggleTaskDone} updateTaskName={mockedUpdateTaskName} />)
    const firstTaskTrashIcon = getByTestId('trash-0');

    fireEvent(firstTaskTrashIcon, 'press');

    expect(mockedRemoveTask).toHaveBeenCalledWith(tasks[0].id);
  });

  it('should be able to handle "toggleTaskDone" event', () => {    
    const { getByText } = render(<TasksList tasks={tasks} removeTask={mockedRemoveTask} toggleTaskDone={mockedToggleTaskDone} updateTaskName={mockedUpdateTaskName} />)
    const secondTask = getByText('Việc 2');

    fireEvent.press(secondTask);

    expect(mockedToggleTaskDone).toHaveBeenCalledWith(tasks[1].id);
  });
})