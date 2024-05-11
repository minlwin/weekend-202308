export const BALANCE_TYPES = ['Income', 'Expense']

export const ROLES = ['Manager', 'Employee']

export const STATUSES = ['Applied', 'Activated', 'Resigned']

export interface PageResult {
  content: any[]
  totalPages: number
  totalElement: number
  size: number
  number: number
}
